package com.example.paydemo.service.pay.impl.WxPay;

import com.example.paydemo.domain.entity.TradeOrder;
import com.example.paydemo.domain.vo.PayResultVO;
import com.example.paydemo.service.PayService;
import com.github.binarywang.wxpay.bean.order.WxPayMwebOrderResult;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * H5  手机网页，会唤醒微信客户端进行支付
 *
 * @author KPQ
 * @date 2021/12/15
 */
@Slf4j
@Component
public class WxMinWebPayServiceImpl extends BaseWxPayService implements PayService<PayResultVO, TradeOrder> {
    @Override
    public PayResultVO pay(TradeOrder param) throws Exception {
        TradeOrder order = createOrder(param).getOrder();
        log.info("-------使用H5支付进行预支付:{}-------", param.getOrderNo());
        WxPayUnifiedOrderRequest wxPayUnifiedOrderRequest = buildOrderRequest(order);
        wxPayUnifiedOrderRequest.setTradeType(WxPayConstants.TradeType.MWEB);
        final WxPayMwebOrderResult result = wxPayService.createOrder(wxPayUnifiedOrderRequest);
        log.info("-------使用H5支付进行预支付返回链接:{}-------", result.getMwebUrl());
        return new PayResultVO(result.getMwebUrl(), order);
    }
}
