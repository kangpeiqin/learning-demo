package com.example.demo.security.config;

import com.example.demo.security.exception.SimpleAuthenticationEntryPoint;
import com.example.demo.security.filter.JwtAuthorizationFilter;
import com.example.demo.security.handler.AuthFailureHandler;
import com.example.demo.security.handler.AuthSuccessHandler;
import com.example.demo.security.handler.SimpleAccessDeniedHandler;
import com.example.demo.security.properties.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author KPQ
 * @date 2021/11/18
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthSuccessHandler authSuccessHandler;

    private final AuthFailureHandler authFailureHandler;

    private final SimpleAccessDeniedHandler accessDeniedHandler;

    private final SimpleAuthenticationEntryPoint authenticationEntryPoint;

    private final JwtProperties jwtProperties;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 配置不需要进行认证的接口
     */
    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers(HttpMethod.OPTIONS, "/**")
                .antMatchers("/css/**")
                .antMatchers("/test");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/login")
                .successHandler(authSuccessHandler)
                .failureHandler(authFailureHandler)
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
//                .logoutSuccessHandler(LogoutSuccessHandler())
                .permitAll()
                .and()
                .headers()
                .frameOptions()
                .disable()
                .and()
                .authorizeRequests()
                .antMatchers("/login.html").permitAll()
                //开启认证
                .anyRequest().authenticated()
                .and().csrf().disable()
                //前后端分离采用JWT 不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                //添加自定义权限过滤器
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler)
                .and()
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), jwtProperties.getSecretKey()));
    }

}
