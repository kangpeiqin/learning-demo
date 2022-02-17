package com.example.demo.javaconfigdemo;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 演示使用Java config 引入bean的几种方式
 *
 * @author KPQ
 * @date 2022/1/21
 */
@Data
@Slf4j
@Component
@MyConditionAnnotation({"key"})
public class Monkey {
    static {
        log.info("========loading monkey==========");
    }

    private String name;

}
