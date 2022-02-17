package com.example.demo.javaconfigdemo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 演示使用Java config 引入bean的几种方式
 *
 * @author KPQ
 * @date 2022/1/21
 */
@Data
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class Tiger {
    static {
        log.info("========loading tiger==========");
    }

    private String name;

}
