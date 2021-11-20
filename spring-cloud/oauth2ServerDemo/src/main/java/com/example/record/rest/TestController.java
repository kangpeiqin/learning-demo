package com.example.record.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kpq
 * @since 1.0.0
 */
@RestController
public class TestController {

    @GetMapping("test")
    public String test(){
        return "test";
    }

}
