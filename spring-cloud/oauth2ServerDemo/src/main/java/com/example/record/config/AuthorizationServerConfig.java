package com.example.record.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * 创建认证服务器：作为统一令牌发放并校验的地方
 * 为应用增加了一系列的REST端点，从而提供一个标准的OAuth 2.0的实现。
 *
 * @author kpq
 * @since 1.0.0
 */
@Configuration
@EnableAuthorizationServer
@Order(1)
public class AuthorizationServerConfig {


}
