package com.example.record.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 在 TopicExchange 中，Queue 通过 routingkey 绑定到 TopicExchange 上，
 * 当消息到达 TopicExchange 后，TopicExchange 根据消息的 routingkey
 * 将消息路由到一个或者多个 Queue 上
 *
 * @author KPQ
 * @date 2022/3/28
 */
@Configuration
public class RabbitTopicConfig {

    public final static String TOPIC_NAME = "test-topic";

    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange(TOPIC_NAME, true, false);
    }

    @Bean
    Queue dev() {
        return new Queue("dev");
    }

    @Bean
    Queue prod() {
        return new Queue("prod");
    }

    @Bean
    Binding bindDev() {
        return BindingBuilder.bind(dev()).to(topicExchange())
                .with("dev.#");
    }

    @Bean
    Binding bindProd() {
        return BindingBuilder.bind(prod()).to(topicExchange())
                .with("prod.#");
    }

}
