package com.example.demo.githubTrending.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author KPQ
 * @date 2021/12/29
 */
@Service
@Slf4j
public class TestService {

    @Async
    public void asyncTest() {
        log.info("异步方法内部线程名称：{}", Thread.currentThread().getName());
    }

    @Async()
    public Future<String> asyncMethod() {
        sleep();
        log.info("异步方法内部线程名称：{}", Thread.currentThread().getName());
        return new AsyncResult<>("hello async");
    }

    private void sleep() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
