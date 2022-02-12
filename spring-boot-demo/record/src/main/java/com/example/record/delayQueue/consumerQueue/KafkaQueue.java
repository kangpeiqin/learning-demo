package com.example.record.delayQueue.consumerQueue;

import com.example.record.delayQueue.container.JobPool;
import com.example.record.delayQueue.model.DelayJob;
import com.example.record.delayQueue.model.Job;
import com.example.record.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author kpq
 * @since 1.0.0
 */
@Component
@Slf4j
public class KafkaQueue implements ConsumerQueue {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private JobPool jobPool;

    @Override
    public void pushJob(DelayJob delayJob) {
        ListenableFuture<SendResult<String, String>> future = this.kafkaTemplate.send("test", JsonUtil.toJson(delayJob));
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.info("成功发送消息：{}，offset=[{}]", JsonUtil.toJson(delayJob), result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(Throwable ex) {
                log.error("消息：{} 发送失败，原因：{}", JsonUtil.toJson(delayJob), ex.getMessage());
            }
        });
    }

    @Override
    @KafkaListener(topics = "test", groupId = "test-consumer")
    public DelayJob popJob(String message) {
        log.info("消费消息: {}", message);
        //获取延时任务
        DelayJob delayJob = JsonUtil.fromJson(message, DelayJob.class);
        Job job = jobPool.getJob(delayJob.getJodId());
        if (Objects.nonNull(job)) {
            //消费任务 TODO
            jobPool.removeDelayJob(job.getId());
        }
        return delayJob;
    }
}
