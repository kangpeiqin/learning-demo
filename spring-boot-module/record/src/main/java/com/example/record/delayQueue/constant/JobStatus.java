package com.example.record.delayQueue.constant;

import lombok.Getter;

/**
 * 任务状态枚举：READY(可执行)、DELAY(等待时间到来)、DELETED(已经消费完成)
 * @author KPQ
 * @date 2021/10/8
 */
@Getter
public enum JobStatus {
    /**
     * 可执行状态，等待消费
     */
    READY,
    /**
     * 不可执行状态，等待时钟周期
     */
    DELAY,
    /**
     * 已被消费者读取，但还未得到消费者的响应
     */
    RESERVED,
    /**
     * 已被消费完成或者已被删除
     */
    DELETED
}
