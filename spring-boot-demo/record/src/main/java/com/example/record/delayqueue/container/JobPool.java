package com.example.record.delayqueue.container;

import cn.hutool.json.JSONUtil;
import com.example.record.delayqueue.model.Job;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;


/**
 * JobPool 任务池：用来存放所有Job的元信息
 * 将任务放进容器当中，新加入的任务都会放到任务池当中
 * 提供了获取任务池，往任务池中加入、获取、删除任务等操作
 *
 * 采用 Redis 的 Hash 数据结构
 *
 * 容器id   任务id     任务详情
 * key     hashKey     hashValue
 *
 * @author KPQ
 * @date 2021/10/8
 */
@Component
@Slf4j
public class JobPool {

    @Autowired
    private RedisTemplate redisTemplate;

    private final String NAME = "job.pool";

    /**
     * 获取任务池
     */
    private BoundHashOperations getPool() {
        BoundHashOperations ops = redisTemplate.boundHashOps(NAME);
        return ops;
    }

    /**
     * 添加任务
     *
     * @param job
     */
    public void addJob(Job job) {
        log.info("任务池添加任务：{}", JSONUtil.toJsonStr(job));
        getPool().put(job.getId(), job);
    }

    /**
     * 从任务池当中获取任务
     * @param jobId
     * @return
     */
    public Job getJob(String jobId) {
        Object o = getPool().get(jobId);
        if (o instanceof Job) {
            return (Job) o;
        }
        return null;
    }

    /**
     * 从任务池中移除任务
     * @param jobId
     */
    public void removeDelayJob (String jobId) {
        log.info("任务池移除任务：{}",jobId);
        // 移除任务
        getPool().delete(jobId);
    }

}
