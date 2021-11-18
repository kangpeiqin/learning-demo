package com.example.demo.security.handler;

import com.example.demo.security.util.ResponseUtil;
import com.example.demo.security.util.ResultUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 当用户尝试访问需要权限才能的REST资源而权限不足的时候，
 * 将调用此方法发送403响应以及错误信息
 *
 * @author KPQ
 * @date 2021/11/18
 */
@Component
public class SimpleAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        ResponseUtil.response(response, ResultUtil.forbidden());
    }
}
