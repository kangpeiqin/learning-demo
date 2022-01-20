package com.example.payDemo.service.pay.impl.AliPay;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.payment.page.models.AlipayTradePagePayResponse;
import com.example.payDemo.config.AliPayProperties;
import com.example.payDemo.domain.entity.TradeOrder;
import com.example.payDemo.domain.vo.PayResultVO;
import com.example.payDemo.service.PayService;
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
