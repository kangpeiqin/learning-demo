package com.example.demo.javaConfigDemo;

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
public class Snake {
    static {
        log.info("========loading Snake==========");
    }

    private String name;

}
