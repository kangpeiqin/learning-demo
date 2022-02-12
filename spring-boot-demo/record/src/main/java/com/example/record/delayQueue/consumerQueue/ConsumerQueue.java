package com.example.record.delayQueue.consumerQueue;

import com.example.record.delayQueue.model.DelayJob;

/**
 * 消费者队列
 *
 * @author kpq
 * @since 1.0.0
 */
public interface ConsumerQueue {

    /**
     * 往消费队列中放入将要执行的任务
     *
     * @param delayJob
     */
    void pushJob(DelayJob delayJob);

    /**
     * 消费任务
     *
     * @param topic
     * @return
     */
    DelayJob popJob(String topic);

}
