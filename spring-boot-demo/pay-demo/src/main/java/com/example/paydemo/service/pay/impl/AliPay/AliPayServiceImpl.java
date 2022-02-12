package com.example.paydemo.service.pay.impl.AliPay;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.payment.page.models.AlipayTradePagePayResponse;
import com.example.paydemo.config.AliPayProperties;
import com.example.paydemo.domain.entity.TradeOrder;
import com.example.paydemo.domain.vo.PayResultVO;
import com.example.paydemo.service.PayService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author KPQ
 * @date 2021/12/9
 */
@Component
public class AliPayServiceImpl extends BaseAliPayService implements PayService<PayResultVO, TradeOrder> {

    @Resource
    private AliPayProperties aliPayProperties;

    @Override
    public PayResultVO pay(TradeOrder param) throws Exception {
        AlipayTradePagePayResponse response = Factory.Payment
                .Page().pay("商品购买", param.getOrderNo(), param.getAmount().toString(), aliPayProperties.getReturnUrl());
        return null;
    }
}
