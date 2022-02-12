package com.example.record.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * @author KPQ
 * @date 2021/11/17
 */
@Configuration
public class AuthorizationTokenConfig {

    /**
     * 声明 内存 TokenStore 实现，用来存储 token 相关.
     * 默认实现有 mysql、redis
     *
     * @return InMemoryTokenStore
     */
    @Bean
    @Primary
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }


}
