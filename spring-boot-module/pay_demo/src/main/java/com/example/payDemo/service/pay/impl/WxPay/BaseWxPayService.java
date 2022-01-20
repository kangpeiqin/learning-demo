package com.example.payDemo.service.pay.impl.WxPay;

import com.example.payDemo.domain.entity.TradeOrder;
import com.example.payDemo.domain.vo.PayResultVO;
import com.example.payDemo.service.PayService;
import com.example.payDemo.util.OrderNumUtil;
import com.github.binarywang.wxpay.bean.request.WxPayRefundQueryRequest;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayOrderQueryResult;
import com.github.binarywang.wxpay.bean.result.WxPayRefundQueryResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author KPQ
 * @date 2021/12/10
 */
@Slf4j
public abstract class BaseWxPayService implements PayService<PayResultVO, TradeOrder> {

    @Resource
    protected WxPayService wxPayService;

    /**
     * 支付日志保存
     *
     * @param param
     */
    @Override
    public void savePayLog(TradeOrder param) {

    }

    /**
     * 微信请求参数构建
     *
     * @param order
     * @return
     */
    protected WxPayUnifiedOrderRequest buildOrderRequest(TradeOrder order) {
        WxPayUnifiedOrderRequest wxPayUnifiedOrderRequest = WxPayUnifiedOrderRequest.newBuilder()
                .outTradeNo(order.getOrderNo())
                .body("商品购买")
                .attach(order.getUserId())
                .totalFee(order.getAmount().multiply(new BigDecimal(100)).intValue())
                .spbillCreateIp(order.getIpAddr())
                .productId(order.getId().toString())
                .build();
        return wxPayUnifiedOrderRequest;
    }

    @Override
    public PayResultVO createOrder(TradeOrder param) throws Exception {
        String orderNo = param.getOrderNo();
        //TODO 根据 orderNo 查询数据库，判断是否产生预支付订单，
        // 依靠前端传值，需要判断支付方式是否被改变
        // 查询订单的金额信息等
        if (orderNo == null) {
            //生成预支付订单号
            param.setOrderNo(OrderNumUtil.genOderNum());
        }
        return new PayResultVO(null, param);
    }

    @Override
    public boolean refund(TradeOrder order) throws Exception {
        WxPayRefundQueryRequest refundQueryRequest = WxPayRefundQueryRequest.newBuilder().outTradeNo(order.getOrderNo()).build();
        WxPayRefundQueryResult refundQueryResult;
        try {
            refundQueryResult = wxPayService.refundQuery(refundQueryRequest);
//            WxPayRefundRequest refundRequest = WxPayRefundRequest.newBuilder()
//                    .transactionId(order.getTractionNo())
//                    .outTradeNo(order.getOrderNo())
//                    .outRefundNo(order.getId())
//                    .notifyUrl(wxPayPropertiesConfig.getRefundNotifyUrl())
//                    .totalFee(order.getAmount().multiply(new BigDecimal(100)).intValue())
//                    .refundFee(order.getRefundAmount().multiply(new BigDecimal(100)).intValue())
//                    .build();
//            WxPayRefundResult wxPayRefundResult = wxPayService.refund(refundRequest);

        } catch (WxPayException e) {
            log.error("退款查询异常{}", e.toString());
            return false;
        }
        return true;
    }

    /**
     * 支付状态轮询
     *
     * @return
     */
    public WxPayOrderQueryResult queryStatus(String orderNo) throws WxPayException {
        WxPayOrderQueryResult queryResult = this.wxPayService.queryOrder(null, orderNo);
        return queryResult;
    }



}
