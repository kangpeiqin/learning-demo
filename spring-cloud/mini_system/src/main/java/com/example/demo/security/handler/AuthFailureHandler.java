package com.example.demo.security.handler;

import com.example.demo.security.util.ResponseUtil;
import com.example.demo.security.util.ResultUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证失败处理逻辑
 *
 * @author KPQ
 * @date 2021/11/18
 */
@Component
public class AuthFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        if (exception instanceof BadCredentialsException) {
            ResponseUtil.response(response, ResultUtil.builder()
                    .code(HttpStatus.UNAUTHORIZED.value()).msg("username or password error").build());
        }else {
            ResponseUtil.response(response, ResultUtil.unauthorized());
        }
    }
}
