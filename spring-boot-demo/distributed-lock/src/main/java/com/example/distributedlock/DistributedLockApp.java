package com.example.distributedlock;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
@MapperScan("com.example.distributedlock.mapper")
@EnableElasticsearchRepositories
public class DistributedLockApp {

    public static void main(String[] args) {
        SpringApplication.run(DistributedLockApp.class, args);
    }

}
