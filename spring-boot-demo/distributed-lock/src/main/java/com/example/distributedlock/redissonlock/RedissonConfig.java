package com.example.distributedlock.redissonLock;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;

/**
 * 基于 Redisson 实现分布式锁
 * Redisson是一个在Redis的基础上实现的Java驻内存数据网格（In-Memory Data Grid）。
 * 它不仅提供了一系列的分布式的Java常用对象，还提供了许多分布式服务
 * 1、Jedis
 * Jedis是Redis官方推出的用于通过Java连接Redis客户端的一个工具包，提供了Redis的各种命令支持
 * 2、Lettuce
 * Lettuce是一种可扩展的线程安全的 Redis 客户端，通讯框架基于Netty，
 * 支持高级的 Redis 特性，比如哨兵，集群，管道，自动重新连接和Redis数据模型。
 * Spring Boot 2.x 开始 Lettuce 已取代 Jedis 成为首选 Redis 的客户端。
 * 3、Redisson
 * 基于Redis、Lua和Netty建立起了成熟的分布式解决方案
 *
 * @author KPQ
 * @date 2021/10/12
 */
@Configuration
public class RedissonConfig {

    @Bean
    public Config RedissonConfig() throws IOException {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("redissonConfig.yaml");
        return Config.fromYAML(inputStream);
    }

    @Bean
    public RedissonClient redissonClient(Config config) {
        return Redisson.create(config);
    }
}
