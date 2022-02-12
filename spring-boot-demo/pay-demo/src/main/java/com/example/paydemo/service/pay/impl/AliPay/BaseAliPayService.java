package com.example.paydemo.service.pay.impl.AliPay;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.payment.common.models.AlipayTradeRefundResponse;
import com.example.paydemo.domain.entity.TradeOrder;
import com.example.paydemo.domain.vo.PayResultVO;
import com.example.paydemo.util.OrderNumUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author KPQ
 * @date 2021/12/16
 */
@Slf4j
public abstract class BaseAliPayService {

    public void savePayLog(TradeOrder param) {

    }

    public PayResultVO createOrder(TradeOrder param) throws Exception {
        String orderNo = param.getOrderNo();
        //TODO 根据 orderNo 查询数据库，判断是否产生预支付订单
        if (orderNo == null) {
            //生成预支付订单号
            param.setOrderNo(OrderNumUtil.genOderNum());
        }
        return new PayResultVO(null, param);
    }

    /**
     * 支付宝退款
     * @param param
     * @return
     * @throws Exception
     */
    public boolean refund(TradeOrder param) throws Exception {
        AlipayTradeRefundResponse refundResponse = Factory.Payment.Common().refund(param.getOrderNo(), param.getAmount().toString());
        return false;
    }

}
