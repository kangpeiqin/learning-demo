package com.example;

import com.example.distributeLock.DistributedLockApp;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author KPQ
 * @date 2021/10/12
 */
@SpringBootTest(classes = DistributedLockApp.class)
@RunWith(SpringRunner.class)
@Slf4j
public class DistributedLockAppTest {

    private final static String LOCK_PREFIX = "redisson:lock:";

    @Resource
    private RedissonClient redissonClient;

    @Test
    public void testOne() {
        RLock lock = redissonClient.getLock(LOCK_PREFIX + 1);
        boolean b = lock.tryLock();
        System.out.println(b);
    }

}
