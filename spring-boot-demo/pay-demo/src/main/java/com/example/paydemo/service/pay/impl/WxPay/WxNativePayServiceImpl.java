package com.example.paydemo.service.pay.impl.WxPay;

import com.example.paydemo.domain.entity.TradeOrder;
import com.example.paydemo.domain.vo.PayResultVO;
import com.example.paydemo.enums.TradeTypeEnum;
import com.example.paydemo.service.PayService;
import com.github.binarywang.wxpay.bean.order.WxPayNativeOrderResult;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Native 微信扫码方式支付实现
 *
 * @author KPQ
 */
@Component
@Slf4j
public class WxNativePayServiceImpl extends BaseWxPayService implements PayService<PayResultVO, TradeOrder> {

    /**
     * 预支付信息返回
     * 创建系统订单 --> 将订单信息和预支付信息返回给前端
     *
     * @param param
     * @return
     * @throws Exception
     */
    @Override
    public PayResultVO pay(TradeOrder param) throws Exception {
        TradeOrder order = createOrder(param).getOrder();
        log.info("-------用户:{},使用原生扫码进行预支付:{}-------", param.getUserId(), param.getOrderNo());
        WxPayUnifiedOrderRequest wxPayUnifiedOrderRequest = buildOrderRequest(order);
        //Native支付方式
        wxPayUnifiedOrderRequest.setTradeType(TradeTypeEnum.NATIVE.getCode());
        //创建预支付订单
        final WxPayNativeOrderResult result = wxPayService.createOrder(wxPayUnifiedOrderRequest);
        byte[] content = wxPayService.createScanPayQrcodeMode2(result.getCodeUrl(), null, null);
        log.info("-------原生扫码预支付链接:{}-------", result.getCodeUrl());
        return new PayResultVO(content, order);
    }

}