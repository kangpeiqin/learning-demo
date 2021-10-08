package com.example.client.service;

import com.example.client.delayQueue.constant.JobStatus;
import com.example.client.delayQueue.container.DelayBucket;
import com.example.client.delayQueue.container.JobPool;
import com.example.client.delayQueue.container.ReadyQueue;
import com.example.client.delayQueue.model.DelayJob;
import com.example.client.delayQueue.model.Job;
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


    public DelayJob addDefJob(Job job) {
        job.setStatus(JobStatus.DELAY);
        jobPool.addJob(job);
        DelayJob delayJob = new DelayJob(job);
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
        Job job = jobPool.getJob(String.valueOf(delayJob.getJodId()));
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

