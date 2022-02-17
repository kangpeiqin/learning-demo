package com.example.demo;

import com.example.demo.javaconfigdemo.EnableAnimal;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableCaching
@EnableAsync
@EnableAnimal
public class GithubTrendingApp {

    public static void main(String[] args) {
        SpringApplication.run(GithubTrendingApp.class, args);
    }

}
