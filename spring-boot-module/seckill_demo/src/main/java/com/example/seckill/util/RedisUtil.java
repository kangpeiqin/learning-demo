package com.example.seckill.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * @author KPQ
 * @date 2022/1/28
 */
@Slf4j
public class RedisUtil {

    @Resource
    private RedisTemplate<Serializable, Serializable> redisTemplate;

}
