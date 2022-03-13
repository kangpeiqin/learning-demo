package com.example.mall.rest;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author kpq
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/auth")
@Api(tags = "登录接口")
public class LoginController {

    @GetMapping("/verifyCode")
    @ApiOperation("验证码获取")
    public void verifyCode(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        LineCaptcha captcha = CaptchaUtil.createLineCaptcha(100, 30);
        HttpSession session = request.getSession(true);
        session.setAttribute("verify_code", captcha.getCode());
        captcha.write(resp.getOutputStream());
    }

}
