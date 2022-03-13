package com.example.mall;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan(basePackages = "com.example.mall.mapper")
@EnableCaching
public class MallApp {

    public static void main(String[] args) {
        SpringApplication.run(MallApp.class, args);
    }

}
