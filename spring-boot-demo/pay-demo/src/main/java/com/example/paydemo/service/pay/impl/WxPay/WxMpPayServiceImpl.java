package com.example.paydemo.service.pay.impl.WxPay;

import com.example.paydemo.domain.entity.TradeOrder;
import com.example.paydemo.domain.vo.PayResultVO;
import com.example.paydemo.service.PayService;
import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * JSAPI 微信公众号支付/小程序支付实现
 *
 * @author KPQ
 * @date 2021/12/15
 */
@Slf4j
@Component
public class WxMpPayServiceImpl extends BaseWxPayService implements PayService<PayResultVO, TradeOrder> {
    @Override
    public PayResultVO pay(TradeOrder param) throws Exception {
        TradeOrder order = createOrder(param).getOrder();
        log.info("-------用户:{},使用公众号支付/小程序进行预支付:{}-------", param.getUserId(), param.getOrderNo());
        WxPayUnifiedOrderRequest wxPayUnifiedOrderRequest = buildOrderRequest(order);
        wxPayUnifiedOrderRequest.setTradeType(WxPayConstants.TradeType.JSAPI);
        final WxPayMpOrderResult result = wxPayService.createOrder(wxPayUnifiedOrderRequest);
        log.info("-------使用公众号支付/小程序进行预支付返回结果:{}-------", result);
        //预支付订单日志保存
        savePayLog(param);
        return new PayResultVO(result, order);
    }
}
