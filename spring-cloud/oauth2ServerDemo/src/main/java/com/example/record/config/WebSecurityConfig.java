package com.example.record.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author KPQ
 * @date 2021/11/17
 */
@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final ClientLogoutSuccessHandler clientLogoutSuccessHandler;
    private final ClientLoginFailureHandler clientLoginFailureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .failureHandler(clientLoginFailureHandler)
                .and().authorizeRequests()
                .antMatchers("/oauth/**")
                .permitAll().anyRequest().authenticated();
    }

    /**
     * 授权管理.
     *
     * @return 认证管理对象
     * @throws Exception 认证异常信息
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
