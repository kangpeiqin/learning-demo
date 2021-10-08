package com.example.client.delayQueue.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 任务引用对象
 *
 * @author KPQ
 * @date 2021/10/8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DelayJob {


    /**
     * 延迟任务的唯一标识
     */
    private String jodId;

    /**
     * 任务的执行时间
     */
    private long delayDate;

    /**
     * 任务类型（具体业务类型）
     */
    private String topic;


    public DelayJob(Job job) {
        this.jodId = job.getId();
        this.delayDate = System.currentTimeMillis() + job.getDelayTime();
        this.topic = job.getTopic();
    }


}
