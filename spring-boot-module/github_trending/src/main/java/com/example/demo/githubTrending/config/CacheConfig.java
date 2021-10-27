package com.example.demo.githubTrending.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * @author KPQ
 * @date 2021/10/27
 */
@Configuration
public class CacheConfig {

    public class CacheName {

        public static final String TRENDING_HOT = "trending_hot";

    }

    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCacheNames(Collections.singletonList(CacheName.TRENDING_HOT));
        cacheManager.setCaffeine(Caffeine.newBuilder()
                .expireAfterAccess(1, TimeUnit.DAYS));
        return cacheManager;
    }


}
