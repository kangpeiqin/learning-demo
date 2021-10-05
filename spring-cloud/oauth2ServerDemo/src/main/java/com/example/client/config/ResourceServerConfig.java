package com.example.client.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @author kpq
 * @since 1.0.0
 */
@Configuration
@EnableResourceServer
@Order(2)
public class ResourceServerConfig {
}
