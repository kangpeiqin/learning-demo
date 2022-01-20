package com.example.payDemo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author KPQ
 * @date 2021/12/16
 */
@ConfigurationProperties(prefix = "alipay")
@Component
@Data
public class AliPayProperties {

    private String appId;

    private String privateKey;

    private String publicKey;

    private String serviceUrl;

    private String domain;

    private String returnUrl;

    private String notifyUrl;

    private String refundNotifyUrl;

}
