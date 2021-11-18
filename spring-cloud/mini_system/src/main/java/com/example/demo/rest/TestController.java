package com.example.demo.rest;

import com.example.demo.security.util.ResultUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public ResultUtil<String> unauthorized(@RequestParam("code") String code) {
        return ResultUtil.success(code);
    }
}
