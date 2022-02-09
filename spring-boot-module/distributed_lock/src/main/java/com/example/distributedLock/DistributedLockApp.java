package com.example.distributedLock;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.distributedLock.mapper")
public class DistributedLockApp {

    public static void main(String[] args) {
        SpringApplication.run(DistributedLockApp.class, args);
    }

}
