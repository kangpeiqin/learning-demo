package com.example.paydemo.config;

import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author KPQ
 * @date 2021/12/10
 */
@Configuration
public class WxConfig {

    @Resource
    private WxPayProperties wxPayProperties;

    /**
     * 微信支付配置
     */
    @Bean
    public WxPayService wxPayService(WxPayConfig payConfig) {
        WxPayService wxPayService = new WxPayServiceImpl();
        wxPayService.setConfig(payConfig);
        return wxPayService;
    }

    @Bean
    @ConditionalOnMissingBean
    public WxPayConfig payConfig() {
        WxPayConfig payConfig = new WxPayConfig();
        payConfig.setAppId(wxPayProperties.getAppId());
        payConfig.setMchId(wxPayProperties.getMchId());
        payConfig.setMchKey(wxPayProperties.getMchKey());
        payConfig.setKeyPath(wxPayProperties.getKeyPath());
        payConfig.setNotifyUrl(wxPayProperties.getNotifyUrl());
        payConfig.setTradeType(WxPayConstants.TradeType.JSAPI);
        payConfig.setHttpTimeout(30 * 1000);
        return payConfig;
    }

}
