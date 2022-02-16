package com.example.record.delayqueue.consumerqueue;

import cn.hutool.json.JSONUtil;
import com.example.record.delayqueue.model.DelayJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 就绪消息队列：存放等待执行的消息
 * 存放处于等待执行状态的任务
 *
 * @author KPQ
 * @date 2021/10/8
 */
@Component
@Slf4j
public class ReadyQueue implements ConsumerQueue {

    @Autowired
    private RedisTemplate redisTemplate;

    private final String NAME = "process.queue.";

    private String getKey(String topic) {
        return NAME + topic;
    }

    /**
     * 获得队列
     *
     * @param topic
     * @return
     */
    private BoundListOperations getQueue(String topic) {
        BoundListOperations ops = redisTemplate.boundListOps(getKey(topic));
        return ops;
    }

    /**
     * 设置任务
     *
     * @param delayJob
     */
    @Override
    public void pushJob(DelayJob delayJob) {
        log.info("执行队列添加任务:{}", delayJob);
        BoundListOperations listOperations = getQueue(delayJob.getTopic());
        listOperations.leftPush(delayJob);
    }

    /**
     * 移除并获得任务
     *
     * @param topic
     * @return
     */
    @Override
    public DelayJob popJob(String topic) {
        BoundListOperations listOperations = getQueue(topic);
        Object o = listOperations.leftPop();
        if (o instanceof DelayJob) {
            log.info("执行队列取出任务:{}", JSONUtil.toJsonStr((DelayJob) o));
            return (DelayJob) o;
        }
        return null;
    }


}
