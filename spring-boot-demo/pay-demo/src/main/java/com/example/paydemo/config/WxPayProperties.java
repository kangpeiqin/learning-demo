package com.example.paydemo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author KPQ
 * @date 2021/12/9
 */
@ConfigurationProperties(prefix = "wxpay")
@Component
@Data
public class WxPayProperties {

    /**
     * 小程序id
     */
    private String appId;

    /**
     * 商户id
     */
    private String mchId;

    /**
     * 商户密钥
     */
    private String mchKey;

    /**
     * 小程序密钥
     */
    private String appSecret;

    /**
     * 证书路径
     */
    private String keyPath;

    /**
     * 支付成功回调接口，由支付系统(微信、支付宝)进行调用
     */
    private String notifyUrl;

    /**
     * 退款回调接口
     */
    private String refundNotifyUrl;

}
