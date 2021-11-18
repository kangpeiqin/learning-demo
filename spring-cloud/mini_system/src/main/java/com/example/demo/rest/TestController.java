package com.example.demo.rest;

import cn.hutool.http.HttpUtil;
import com.example.demo.security.util.ResultUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

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
        Map<String, Object> params = new HashMap<>(16);
        params.put("grant_type","authorization_code");
        params.put("code",code);
        params.put("redirect_uri","http://127.0.0.1:8089/test");
        params.put("scope", "READ");
        params.put("clientId", "oauth2");
        Map<String,String> headers = new HashMap<>(16);
        headers.put("Authorization", "Basic b2F1dGgyOm9hdXRoMg==");
        headers.put("Content-Type", "application/x-www-form-urlencoded");
        String res = HttpUtil.createPost(url)
                .form(params)
                .addHeaders(headers)
                .execute().body();
        return ResultUtil.success(res);
    }
}
