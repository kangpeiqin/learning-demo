package com.example.demo.githubTrending.rest;

import com.example.demo.githubTrending.service.TestService;
import com.example.demo.util.HttpUtil;
import com.example.demo.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.Future;

/**
 * @author KPQ
 * @date 2021/12/29
 */
@RestController
@Slf4j
public class TestController {

    @Resource
    private TestService testService;

    @GetMapping("test")
    public String test() throws Exception {
        testService.asyncTest();
        Future<String> stringFuture = testService.asyncMethod();
        String result = stringFuture.get();
        log.info("异步方法返回值：{}", result);
        Result res = HttpUtil.get("http://api.money.126.net/data/feed/1000002,1000001,1000881,0601398,money.api");
        String obj = res.getData().toString().replace("_ntes_quote_callback(", "")
                .replace(");", "");
        return obj;
    }


    public static void main(String[] args) {
        Result res = HttpUtil.get("http://api.money.126.net/data/feed/1000002,1000001,1000881,0601398,money.api");
        System.out.println(res.getData().toString().replace("_ntes_quote_callback(", "")
                .replace(");", ""));
    }

}
