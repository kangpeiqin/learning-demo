package com.example.record.delayQueue.timer;

import com.example.record.delayQueue.container.DelayBucket;
import com.example.record.delayQueue.container.JobPool;
import com.example.record.delayQueue.container.ReadyQueue;
import com.example.record.delayQueue.handler.DelayJobHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 设置了线程池为每个bucket设置一个轮询操作
 **/
@Component
public class DelayTimer implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private DelayBucket delayBucket;
    @Autowired
    private JobPool jobPool;
    @Autowired
    private ReadyQueue readyQueue;

    @Value("${thread.size:1}")
    private int length;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        ExecutorService executorService = new ThreadPoolExecutor(length, length, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
        for (int i = 0; i < length; i++) {
            executorService.execute(new DelayJobHandler(delayBucket, jobPool, readyQueue, i));
        }

    }
}
