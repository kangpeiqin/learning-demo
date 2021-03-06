package com.example.mall.security;

import com.example.mall.security.filter.LoginFilter;
import com.example.mall.entity.SysUser;
import com.example.mall.service.SysUserService;
import com.example.mall.util.ObjectMapperUtil;
import com.example.mall.util.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
import org.springframework.security.web.session.ConcurrentSessionFilter;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author kpq
 * @since 1.0.0
 */
@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final SysUserService userService;

    private final SecurityMetaDataSource securityMetaDataSource;

    private final CustomUrlDecisionManager customUrlDecisionManager;

    private static final String APPLICATION_JSON_UTF8 = "application/json;charset=UTF-8";

    @Bean
    SessionRegistryImpl sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    LoginFilter loginFilter() throws Exception {
        LoginFilter loginFilter = new LoginFilter(sessionRegistry());
        //??????????????????????????????json??????
        loginFilter.setAuthenticationSuccessHandler((request, response, authentication) -> {
                    response.setContentType(APPLICATION_JSON_UTF8);
                    PrintWriter out = response.getWriter();
                    SysUser user = (SysUser) authentication.getPrincipal();
                    user.setPassword(null);
                    Result<SysUser> result = Result.ok("????????????!", user);
                    out.write(ObjectMapperUtil.writeValueAsString(result));
                    out.flush();
                    out.close();
                }
        );
        //??????????????????
        loginFilter.setAuthenticationFailureHandler((request, response, exception) -> {
                    response.setContentType(APPLICATION_JSON_UTF8);
                    PrintWriter out = response.getWriter();
                    Result result = Result.error(exception.getMessage());
                    if (exception instanceof LockedException) {
                        result.setMsg("????????????????????????????????????!");
                    } else if (exception instanceof CredentialsExpiredException) {
                        result.setMsg("?????????????????????????????????!");
                    } else if (exception instanceof AccountExpiredException) {
                        result.setMsg("?????????????????????????????????!");
                    } else if (exception instanceof DisabledException) {
                        result.setMsg("????????????????????????????????????!");
                    } else if (exception instanceof BadCredentialsException) {
                        result.setMsg("???????????????????????????");
                    }
                    out.write(ObjectMapperUtil.writeValueAsString(result));
                    out.flush();
                    out.close();
                }
        );
        //?????????????????????
        loginFilter.setAuthenticationManager(authenticationManagerBean());
        loginFilter.setFilterProcessesUrl("/api/auth/login");
        ConcurrentSessionControlAuthenticationStrategy sessionStrategy = new ConcurrentSessionControlAuthenticationStrategy(sessionRegistry());
        //?????? session ??????????????????
        sessionStrategy.setMaximumSessions(1);
        loginFilter.setSessionAuthenticationStrategy(sessionStrategy);
        return loginFilter;
    }


    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**",
                "/index.html", "/img/**",
                "/fonts/**", "/favicon.ico",
                "/api/login/verifyCode", "/doc.html",
                "/webjars/**", "/swagger-resources/**", "/v2/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                        object.setSecurityMetadataSource(securityMetaDataSource);
                        object.setAccessDecisionManager(customUrlDecisionManager);
                        return object;
                    }
                })
                .and()
                .logout()
                .logoutSuccessHandler((req, resp, authentication) -> {
                            resp.setContentType(APPLICATION_JSON_UTF8);
                            PrintWriter out = resp.getWriter();
                            out.write(new ObjectMapper().writeValueAsString(Result.ok("????????????!")));
                            out.flush();
                            out.close();
                        }
                ).permitAll()
                .and()
                .csrf().disable().exceptionHandling()
                //???????????????????????????????????????
                .authenticationEntryPoint((req, resp, authException) -> {
                            resp.setContentType(APPLICATION_JSON_UTF8);
                            resp.setStatus(HttpStatus.UNAUTHORIZED.value());
                            PrintWriter out = resp.getWriter();
                            Result res = Result.unauthorized("????????????");
                            if (authException instanceof InsufficientAuthenticationException) {
                                res.setMsg("???????????????");
                            }
                            out.write(ObjectMapperUtil.writeValueAsString(res));
                            out.flush();
                            out.close();
                        }
                );
        http.addFilterAt(new ConcurrentSessionFilter(sessionRegistry(), event -> {
            HttpServletResponse resp = event.getResponse();
            resp.setContentType(APPLICATION_JSON_UTF8);
            resp.setStatus(HttpStatus.UNAUTHORIZED.value());
            PrintWriter out = resp.getWriter();
            out.write(ObjectMapperUtil.writeValueAsString(Result.error("??????????????????????????????????????????????????????!")));
            out.flush();
            out.close();
        }), ConcurrentSessionFilter.class);
        http.addFilterAt(loginFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    public static void main(String[] args) {
        String encodePass = new BCryptPasswordEncoder().encode("123456");
        System.out.println(encodePass);
    }
}
