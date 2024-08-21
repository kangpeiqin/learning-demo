package com.example.config;

import com.example.constant.DelayConstant;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author x
 * 使用 RabbitMQ 实现延时队列，订单超时取消功能
 * 延迟消息 指的是需要延迟消费的消息
 * 就是当消息发送之后，并不想让消费者立即拿到消息，
 * 而是等待特定时长后，消费者才拿到消息进行消费
 * <p>
 * 订单十分钟内未支付则关闭，短期内未支付的订单数据可能会有很多，
 * 活动期间甚至会达到百万甚至千万级别，对这么庞大的数据量仍旧使用轮询的方式显然是不可取的，
 * 很可能在一秒内无法完成所有订单的检查，同时会给数据库带来很大压力，无法满足业务要求而且性能低下。
 * <p>
 * 一般机票下单之后会要求在 30 分钟之内支付，如果 30 分钟之后没有支付则取消该订单（为什么要取消，因为你站位了又不支付，不能影响别人买票）
 * <p>
 * 当下单后我们把订单信息发送到 MQ 的延时队列中，并设置 30 分钟过期，
 * 30 分钟以后延时队列的数据在转发到死信队列中去，然后我们从死信队列中获取订单信息，
 * 并判断它的支付状态，如果已经支付，不做任何处理，如果未支付，则取消订单。
 * <p>
 * https://juejin.cn/post/7111501986440151071?searchId=20240820155615124FF1B1AF0D880148B0
 */
@Configuration
public class DelayRabbitConfig {

    /**
     * 1、死信队列，消息超出时长，则会被路由到这个队列当中，消费者直接从这个队列当中获取消息
     */
    @Bean
    public Queue deadOrderQueue() {
        return QueueBuilder.durable(DelayConstant.DEAD_ORDER_QUEUE_NAME).build();
    }

    /**
     * 2.死信交换机
     * 通过死信交换机把死信消息发送到指定的队列中去
     * 将路由键和某模式进行匹配。此时队列需要绑定要一个模式上。
     */
    @Bean
    public TopicExchange deadOrderTopicExchange() {
        return ExchangeBuilder.topicExchange(DelayConstant.DEAD_ORDER_EXCHANGE_NAME).durable(true).build();
    }

    /**
     * 3.死信队列（绑定交换机）
     */
    @Bean
    public Binding deadOrderBinding() {
        return BindingBuilder.bind(deadOrderQueue()).to(deadOrderTopicExchange()).with(DelayConstant.DEAD_ORDER_ROUTING_KEY);
    }

    /**
     * 延时消息队列，放入这个队列的消息会有一个过期时间，如果消息过期，就会被转发到死信交换机当中
     *
     * @return
     */
    @Bean
    public Queue orderDelayQueue() {
        return QueueBuilder
                .durable(DelayConstant.ORDER_DELAY_QUEUE)
                // TTL for messages in this queue (30 minutes)
//                .ttl(1800000)
                .ttl(1000 * 60)
                // Dead-letter exchange to forward messages when TTL expires
                .deadLetterExchange(DelayConstant.DEAD_ORDER_EXCHANGE_NAME)
                // Dead-letter routing key to use when forwarding messages
                .deadLetterRoutingKey(DelayConstant.DEAD_ORDER_ROUTING_KEY)
                .build();
    }


    /**
     * 5.延时队列绑定到交换机上，要求该消息与一个特定的路由键完全匹配。
     *
     * @return DirectExchange
     */
    @Bean
    public DirectExchange orderDelayExchange() {
        return ExchangeBuilder.directExchange(DelayConstant.ORDER_DELAY_EXCHANGE).durable(true).build();
    }

    /**
     * 6.延时队列绑定交换机
     */
    @Bean
    public Binding dlxBinding() {
        return BindingBuilder.bind(orderDelayQueue()).to(orderDelayExchange()).with(DelayConstant.ORDER_DELAY_ROUTING_KEY);
    }

}
