package com.example.distributedLock.rest;

import org.springframework.web.bind.annotation.RestController;

/**
 * @author KPQ
 * @date 2021/12/23
 */
@RestController
public class TestController {

    public String test() {
        return "success";
    }


}
