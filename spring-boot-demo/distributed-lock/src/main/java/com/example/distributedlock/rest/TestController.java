package com.example.distributedlock.rest;

import com.example.distributedlock.idempotent.annotation.Idempotent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author KPQ
 * @date 2021/12/23
 */
@RestController
@Slf4j
public class TestController {

    @Value("${customize.code:1}")
    private int value;

    @Idempotent(throwIfRepeat = false)
    @GetMapping("count")
    public String test() {
        return String.valueOf(value);
    }


}
