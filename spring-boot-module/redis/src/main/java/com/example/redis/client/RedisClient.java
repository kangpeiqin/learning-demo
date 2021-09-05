package com.example.redis.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisPool;

import java.net.URI;


/**
 * @author kpq
 * @since 1.0.0
 */
public class RedisClient {
    private static Logger log = LoggerFactory.getLogger(RedisClient.class);
    private static volatile RedisClient instance = null;

    private static JedisPool jedisPool;

    private RedisClient(String ip, int port) {
        try {
            if (jedisPool == null) {
                jedisPool = new JedisPool(new URI("http://" + ip + ":" + port));
            }
        } catch (Exception e) {
            log.error("连接参数错误{}", e.getMessage());
        }
    }

    public static RedisClient getInstance(String ip, final int port) {
        if (instance == null) {
            synchronized (RedisClient.class) {
                if (instance == null) {
                    instance = new RedisClient(ip, port);
                }
            }
        }
        return instance;
    }


}
