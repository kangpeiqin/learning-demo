package com.example.record.service;

import com.example.record.delayQueue.constant.JobStatus;
import com.example.record.delayQueue.container.DelayBucket;
import com.example.record.delayQueue.container.JobPool;
import com.example.record.delayQueue.consumerQueue.ReadyQueue;
import com.example.record.delayQueue.model.DelayJob;
import com.example.record.delayQueue.model.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author KPQ
 * @date 2021/10/8
 */
@Component
public class JobService {

    @Autowired
    private DelayBucket delayBucket;

    @Autowired
    private ReadyQueue readyQueue;

    @Autowired
    private JobPool jobPool;


    /**
     *  用户提交任务：比如下订单，订单状态为未支付，30分钟后未支付的订单状态改为订单取消
     *  1、设置任务状态为延迟状态，往任务池当中添加任务
     *  2、计算任务的执行时间：当前系统时间+延迟的时间，得出任务将要在某一时刻执行
     *  3、将任务加入延时队列：等待被执行
     */
    public DelayJob addDefJob(Job job) {
        job.setTopic("order");
        job.setStatus(JobStatus.DELAY);
        // 延迟执行的时间 2 分钟
        job.setDelayTime(120000);
        //往任务池当中添加任务
        jobPool.addJob(job);
        //创建任务引用对象(计算触发执行的具体时间)
        DelayJob delayJob = new DelayJob(job);
        //往延迟桶队列添加任务
        delayBucket.addDelayJob(delayJob);
        return delayJob;
    }

    /**
     * 获取
     *
     * @return
     */
    public Job getProcessJob(String topic) {
        // 拿到任务
        DelayJob delayJob = readyQueue.popJob(topic);
        if (delayJob == null) {
            return new Job();
        }
        Job job = jobPool.getJob(delayJob.getJodId());
        // 元数据已经删除，则取下一个
        if (job == null) {
            job = getProcessJob(topic);
        }
        return job;
    }

    /**
     * 完成一个执行的任务
     *
     * @param jobId
     * @return
     */
    public void finishJob(String jobId) {
        jobPool.removeDelayJob(jobId);
    }

    /**
     * 完成一个执行的任务
     *
     * @param jobId
     * @return
     */
    public void deleteJob(String jobId) {
        jobPool.removeDelayJob(jobId);
    }

}

