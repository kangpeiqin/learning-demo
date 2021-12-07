package com.example.mointor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import oshi.SystemInfo;

/**
 * @author KPQ
 * @date 2021/12/7
 */
@Configuration
public class BeanConfig {

    @Bean
    public SystemInfo systemInfo() {
        return new SystemInfo();
    }
}
