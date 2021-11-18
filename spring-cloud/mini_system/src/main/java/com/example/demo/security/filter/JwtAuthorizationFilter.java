package com.example.demo.security.filter;


import com.example.demo.security.constant.SecurityConstants;
import com.example.demo.security.util.JwtUtil;
import com.example.demo.security.util.ResponseUtil;
import com.example.demo.security.util.ResultUtil;
import io.jsonwebtoken.JwtException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author KPQ
 * @date 2021/11/18
 */
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final SecretKey secretKey;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, SecretKey secretKey) {
        super(authenticationManager);
        this.secretKey = secretKey;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        String token = request.getHeader(SecurityConstants.AUTHORIZATION);
        if (token == null || !token.startsWith(SecurityConstants.BEARER)) {
            SecurityContextHolder.clearContext();
            chain.doFilter(request, response);
            return;
        }
        String tokenValue = token.replace(SecurityConstants.BEARER, "");
        UsernamePasswordAuthenticationToken authentication = null;
        try {
            authentication = JwtUtil.getAuthentication(tokenValue, secretKey);
        } catch (JwtException e) {
            logger.error("Invalid jwt : " + e.getMessage());
            ResponseUtil.response(response, ResultUtil.unauthorized());
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }
}
