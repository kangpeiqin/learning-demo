package com.example.record.rest.web;

import com.example.record.rabbitmq.RabbitDirectConfig;
import com.example.record.rabbitmq.RabbitFanoutConfig;
import com.example.record.rabbitmq.RabbitTopicConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author kpq
 * @since 1.0.0
 */
@RestController
@Slf4j
public class SendMessageController {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    @Resource
    private RabbitTemplate rabbitTemplate;


    @GetMapping("send/{message}")
    public void send(@PathVariable String message) {
        ListenableFuture<SendResult<String, String>> future = this.kafkaTemplate.send("test", message);
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.info("成功发送消息：{}，offset=[{}]", message, result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(Throwable ex) {
                log.error("消息：{} 发送失败，原因：{}", message, ex.getMessage());
            }
        });
    }

    @GetMapping("sendMsg")
    public String sendMsg() {
        //路由键：routingKey，发送内容。默认情况下：当一条消息到达 DirectExchange 时会被转发到与该条消息 routing key 相同的 Queue 上
        rabbitTemplate.convertAndSend(RabbitDirectConfig.HELLO_WORLD_QUEUE_NAME, "hello");
        return "success";
    }

    @GetMapping("fanoutTest")
    public String fanoutTest() {
        rabbitTemplate.convertAndSend(RabbitFanoutConfig.FANOUT_NAME,
                null, "hello fanout!");
        return "success";
    }

    @GetMapping("topicTest")
    public String topicTest() {
        rabbitTemplate.convertAndSend(RabbitTopicConfig.TOPIC_NAME,
                "dev.queue", "test dev");
        rabbitTemplate.convertAndSend(RabbitTopicConfig.TOPIC_NAME,
                "prod.queue", "test prod");
        return "success";
    }

}
