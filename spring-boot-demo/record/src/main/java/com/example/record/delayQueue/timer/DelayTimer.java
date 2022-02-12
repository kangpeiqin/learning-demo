package com.example.record.delayQueue.timer;

import com.example.record.delayQueue.consumerQueue.ConsumerQueue;
import com.example.record.delayQueue.container.DelayBucket;
import com.example.record.delayQueue.container.JobPool;
import com.example.record.delayQueue.consumerQueue.ReadyQueue;
import com.example.record.delayQueue.handler.DelayJobHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 通过实现这个接口，传入一个泛型事件，在run方法中就可以监听这个事件，从而做出一定的逻辑
 *
 * 设置了线程池为每个bucket设置一个轮询操作
 **/
@Component
@Slf4j
public class DelayTimer implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private DelayBucket delayBucket;

    @Autowired
    private JobPool jobPool;

    @Resource(name = "kafkaQueue")
    private ConsumerQueue readyQueue;

    @Value("${thread.size:1}")
    private int length;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        //创建线程池
        ExecutorService executorService = new ThreadPoolExecutor(length, length, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
        //执行任务
        for (int i = 0; i < length; i++) {
            executorService.execute(new DelayJobHandler(delayBucket, jobPool, readyQueue, i));
        }

    }
}
