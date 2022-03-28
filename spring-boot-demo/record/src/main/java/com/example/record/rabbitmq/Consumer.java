package com.example.record.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author KPQ
 * @date 2022/3/28
 */
@Component
@Slf4j
public class Consumer {

    @RabbitListener(queues = RabbitDirectConfig.HELLO_WORLD_QUEUE_NAME)
    public void consume(String msg) {
        log.info("consume message:{}", msg);
        System.out.println("msg = " + msg);
    }

    /**
     * 一个队列对应了多个消费者，默认情况下，由队列对消息进行平均分配，消息会被分到不同的消费者手中
     */
    @RabbitListener(queues = RabbitDirectConfig.HELLO_WORLD_QUEUE_NAME, concurrency = "10")
    public void receive(String msg) {
        log.info("receive msg {},{}", msg, Thread.currentThread().getName());
    }

    @RabbitListener(queues = "queue-one")
    public void fanoutHandler1(String message) {
        System.out.println("FanoutReceiver:handler1:" + message);
    }

    @RabbitListener(queues = "queue-two")
    public void fanoutHandler2(String message) {
        System.out.println("FanoutReceiver:handler2:" + message);
    }

    @RabbitListener(queues = "dev")
    public void topicHandler1(String message) {
        System.out.println("dev:" + message);
    }

    @RabbitListener(queues = "prod")
    public void topicHandler2(String message) {
        System.out.println("prod:" + message);
    }
}
