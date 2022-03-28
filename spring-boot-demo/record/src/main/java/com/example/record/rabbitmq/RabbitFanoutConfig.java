package com.example.record.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * FanoutExchange 的数据交换策略是把所有到达 FanoutExchange
 * 的消息转发给所有与它绑定的 Queue 上，在这种策略中，routingkey 将不起任何作用
 *
 * @author KPQ
 * @date 2022/3/28
 */
@Configuration
public class RabbitFanoutConfig {

    public final static String FANOUT_NAME = "test-fanout";

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(FANOUT_NAME, true, false);
    }

    @Bean
    Queue queueOne() {
        return new Queue("queue-one");
    }

    @Bean
    Queue queueTwo() {
        return new Queue("queue-two");
    }

    @Bean
    Binding bindingOne() {
        return BindingBuilder.bind(queueOne()).to(fanoutExchange());
    }

    @Bean
    Binding bindingTwo() {
        return BindingBuilder.bind(queueTwo()).to(fanoutExchange());
    }

}
