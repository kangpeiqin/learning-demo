package com.example.record.config;

import com.example.record.service.SysClientDetailsService;
import com.example.record.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * 1、授权服务器的配置： 设置授权服务器如何读取客户端、用户信息和一些端点配置。
 * <p>
 * 授权服务器：为应用增加了一系列的REST端点，从而提供一个标准的OAuth 2.0的实现。
 *
 * @author kpq
 * @since 1.0.0
 */
@Configuration
@EnableAuthorizationServer
@RequiredArgsConstructor
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private final SysClientDetailsService sysClientDetailsService;

    private final SysUserService sysUserService;

    private final AuthenticationManager authenticationManager;

    private final TokenStore tokenStore;

    /**
     * 安全策略配置
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {

    }

    /**
     * 配置如何读取客户端信息：从数据库当中读取
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(sysClientDetailsService);
    }

    /**
     * 认证端点的配置，如：配置token的获取方式配置(从数据库当中获取、内存中获取、缓存中获取...)
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(sysUserService)
                .tokenStore(tokenStore);
    }

}
