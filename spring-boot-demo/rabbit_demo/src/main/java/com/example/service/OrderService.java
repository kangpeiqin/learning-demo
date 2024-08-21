package com.example.service;

import com.example.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zzz
 * @date 2024/8/20 17:19
 */
@Service
@Slf4j
public class OrderService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendOrderMessage(Order order, String messageId, String exchangeName, String key) {
        /* 确认的回调 确认消息是否到达 Broker 服务器 其实就是是否到达交换器
         * 如果发送时候指定的交换器不存在 ack 就是 false 代表消息不可达
         */
//        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
//            log.info("【订单成功进入延时队列：{}】, ack:{}", order.toString(), ack);
//            if (!ack) {
//                //保证消息发送的可靠性，重新发送消息
//                log.info("进行对应的消息补偿机制");
//            }
//            //ack 为 true，说明消息成功发送到消息队列当中
//        });
        // 在实际中ID 应该是全局唯一 能够唯一标识消息 消息不可达的时候触发ConfirmCallback回调方法时可以获取该值，进行对应的错误处理
        CorrelationData correlationData = new CorrelationData(messageId);
        rabbitTemplate.convertAndSend(exchangeName, key, order, message -> {
            /**
             * 消息后续处理
             * 如果配置了 params.put("x-message-ttl", 60 * 1000 * 30);
             * 那么这一句也可以省略,具体根据业务需要是声明 Queue 的时候就指定好延迟时间还是在发送自己控制时间
             */
//            message.getMessageProperties().setExpiration(1000 * 10 + "");
            return message;
        }, correlationData);
//        rabbitTemplate.convertAndSend(exchangeName, key, order);
    }

}
