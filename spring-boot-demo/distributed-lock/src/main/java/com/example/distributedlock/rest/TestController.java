package com.example.distributedlock.rest;

import com.example.distributedlock.idempotent.annotation.Idempotent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author KPQ
 * @date 2021/12/23
 */
@RestController
@Slf4j
public class TestController {

    @Idempotent(throwIfRepeat = false)
    @GetMapping("count")
    public String test() {
        return "success";
    }


}
