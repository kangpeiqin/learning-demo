package com.example.mall.security.filter;

import com.example.mall.entity.SysUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author kpq
 * @since 1.0.0
 */
@RequiredArgsConstructor
@Slf4j
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final SessionRegistry sessionRegistry;

    private static ObjectMapper objectMapper = new ObjectMapper();

    private final static String POST_METHOD = "POST";

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals(POST_METHOD)) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        } else {
            // 从 session 当中获取验证码
            String veryCode = (String) request.getSession().getAttribute("verify_code");
            //采用 JSON 传递数据
            if (request.getContentType().contains(MediaType.APPLICATION_JSON_VALUE)) {
                Map<String, String> loginData = Maps.newHashMap();
                try {
                    //获取登录数据
                    loginData = objectMapper.readValue(request.getInputStream(), Map.class);
                } catch (IOException e) {
                    log.error("error parse loginData data:{}", e.getMessage(), e);
                } finally {
                    //登录验证码校验
                    checkCode(loginData.get("code"), veryCode);
                }
                String username = loginData.get(getUsernameParameter());
                username = username != null ? username : "";
                String password = loginData.get(getPasswordParameter());
                password = password != null ? password : "";
                username = username.trim();
                //令牌构建
                UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                        username, password);
                setDetails(request, authRequest);
                SysUser principal = new SysUser();
                principal.setUsername(username);
                //将用户信息放入到 session 当中
                sessionRegistry.registerNewSession(request.getSession(true).getId(), principal);
                //授权
                return this.getAuthenticationManager().authenticate(authRequest);
            } else {
                //采用表单方式进行传递数据
                checkCode(request.getParameter("code"), veryCode);
                return super.attemptAuthentication(request, response);
            }
        }
    }

    private void checkCode(String code, String verifyCode) {
        if (!StringUtils.hasLength(code) || !StringUtils.hasLength(verifyCode) ||
                !verifyCode.toLowerCase().equals(code.toLowerCase())) {
            //验证码不正确
//            throw new AuthenticationServiceException("验证码不正确");
        }
    }

}
