package com.example.demo.javaConfigDemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author KPQ
 * @date 2022/1/21
 */
@Configuration
public class AnimalRegisterConfiguration {

    @Bean
    public Cat cat() {
        return new Cat("cat");
    }

}
