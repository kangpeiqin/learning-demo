package com.example.demo.security.handler;

import com.example.demo.security.properties.JwtProperties;
import com.example.demo.security.util.JwtUtil;
import com.example.demo.security.util.ResponseUtil;
import com.example.demo.security.util.ResultUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证成功处理逻辑，继承SimpleUrlAuthenticationSuccessHandler或者实现AuthenticationSuccessHandler
 *
 * @author KPQ
 * @date 2021/11/18
 */
@Component
public class AuthSuccessHandler implements AuthenticationSuccessHandler {

    @Resource
    private JwtProperties jwtProperties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //认证成功后创建token
        UserDetails user = (UserDetails) authentication.getPrincipal();
        String token = JwtUtil.generateToken(1L, user.getUsername(), jwtProperties.getExpiration(), jwtProperties.getSecretKey());
        ResponseUtil.response(response, ResultUtil.success(token));
    }

}
