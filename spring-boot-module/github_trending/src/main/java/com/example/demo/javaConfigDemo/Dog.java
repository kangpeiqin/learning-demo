package com.example.demo.javaConfigDemo;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 演示使用Java config 引入bean的几种方式
 *
 * @author KPQ
 * @date 2022/1/21
 */
@Data
@Slf4j
public class Dog {
    static {
        log.info("========loading dog==========");
    }

    private String name;

}
