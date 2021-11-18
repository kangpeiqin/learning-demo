package com.example.demo.rest;

import cn.hutool.http.HttpRequest;
import com.example.demo.security.util.ResultUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author KPQ
 * @date 2021/11/18
 */
@RestController
public class TestController {

    @GetMapping("/api/test")
    public ResultUtil<String> test() {
        return ResultUtil.success("success");
    }

    @GetMapping("/test")
    public ResultUtil<String> unauthorized(@RequestParam("code") String code) throws UnsupportedEncodingException {
        final String url = "http://127.0.0.1:8080/oauth/token";
        String res = HttpRequest.post(url)
                .body(URLEncoder.encode("grant_type", "utf-8"), URLEncoder.encode("authorization_code", "utf-8"))
                .body("code", URLEncoder.encode(code, "utf-8"))
                .body("scope", "READ")
                .body("redirect_uri", "http://127.0.0.1:8089/test")
                .body("clientId", "oauth2")
                .header("Authorization", "Basic b2F1dGgyOm9hdXRoMg==")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .execute().body();
        return ResultUtil.success(res);
    }
}
