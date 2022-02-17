package com.example.demo.javaconfigdemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author KPQ
 * @date 2022/1/21
 */
@Component
@Slf4j
public class User {
    static {
      log.info("------loading user---------");
    }
    private String name;
}
