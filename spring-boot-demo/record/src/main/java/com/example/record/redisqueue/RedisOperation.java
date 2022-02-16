package com.example.record.redisqueue;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * @author KPQ
 * @date 2022/2/16
 */
@Component
@AllArgsConstructor
public class RedisOperation {

    private final RedisTemplate<String, Serializable> redisTemplate;

    private final ValueOperations<String, Serializable> valueOperations;

    private final ListOperations<String, Serializable> listOperations;

    public <T> T get(String key) {
        return (T) valueOperations.get(key);
    }

    public void set(String key, Serializable value) {
        valueOperations.set(key, value);
    }

    public void setnx(String key, Serializable value, long expire) {
        valueOperations.set(key, value, expire);
    }

    public Long increment(final String key, int delta) {
        return valueOperations.increment(key, delta);
    }

    public void leftPush(String key, Serializable value) {
        listOperations.leftPush(key, value);
    }

    public void expire(final String key, long expire, TimeUnit timeUnit) {
        redisTemplate.expire(key, expire, timeUnit);
    }

}
