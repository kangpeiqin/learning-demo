package com.example.record.delayQueue.container;

import cn.hutool.json.JSONUtil;
import com.example.record.delayQueue.model.DelayJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.BoundZSetOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 一组以时间为维度的有序队列，使用可排序的ZSet保存数据，提供取出最小值等操作
 *
 * 桶的标识    具体任务   执行时间
 * key        value       score
 *
 * Redis 有序集合：是string类型元素的集合,且不允许重复的元素。每个元素都会关联一个double类型的分数。通过分数来为集合中的成员进行从小到大的排序。
 * 集合是通过哈希表实现的
 *
 * @author KPQ
 * @date 2021/10/8
 */
@Component
@Slf4j
public class DelayBucket {

    @Autowired
    private RedisTemplate redisTemplate;

    private static AtomicInteger index = new AtomicInteger(0);

    @Value("${thread.size:1}")
    private int bucketsSize;

    private List<String> bucketNames = new ArrayList<>();

    /**
     * 创建 Delay bucket
     */
    @Bean
    public List<String> createBuckets() {
        for (int i = 0; i < bucketsSize; i++) {
            bucketNames.add("bucket" + i);
        }
        return bucketNames;
    }

    /**
     * 获得桶的名称
     *
     * @return
     */
    private String getThisBucketName() {
        int thisIndex = index.addAndGet(1);
        int i1 = thisIndex % bucketsSize;
        return bucketNames.get(i1);
    }

    /**
     * 获得桶集合
     *
     * @param bucketName
     * @return
     */
    private BoundZSetOperations getBucket(String bucketName) {
        return redisTemplate.boundZSetOps(bucketName);
    }

    /**
     * 放入延时任务
     *
     * @param job
     */
    public void addDelayJob(DelayJob job) {
        log.info("添加延迟任务:{}", JSONUtil.toJsonStr(job));
        String thisBucketName = getThisBucketName();
        BoundZSetOperations bucket = getBucket(thisBucketName);
        bucket.add(job, job.getDelayDate());
    }

    /**
     * 获得最新的延期任务
     *
     * @return
     */
    public DelayJob getFirstDelayTime(Integer index) {
        String name = bucketNames.get(index);
        BoundZSetOperations bucket = getBucket(name);
        Set<ZSetOperations.TypedTuple> set = bucket.rangeWithScores(0, 1);
        if (CollectionUtils.isEmpty(set)) {
            return null;
        }
        ZSetOperations.TypedTuple typedTuple = (ZSetOperations.TypedTuple) set.toArray()[0];
        Object value = typedTuple.getValue();
        if (value instanceof DelayJob) {
            return (DelayJob) value;
        }
        return null;
    }

    /**
     * 移除延时任务
     *
     * @param index
     * @param delayJob
     */
    public void removeDelayTime(Integer index, DelayJob delayJob) {
        String name = bucketNames.get(index);
        BoundZSetOperations bucket = getBucket(name);
        bucket.remove(delayJob);
    }

}
