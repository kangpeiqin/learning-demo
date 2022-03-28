package com.example.record.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * reference: https://juejin.cn/post/7051469607806173221
 *
 * 生产者 -- 交换机 -- 队列
 * 四种交换机：Direct、Fanout、Topic、Header
 * 路由策略：
 * 1、Direct: 将消息队列绑定到一个 DirectExchange 上，当一条消息到达
 * DirectExchange 时会被转发到与该条消息 routing key 相同的 Queue 上
 *
 * @author kpq
 * @since 1.0.0
 */
@Configuration
public class RabbitDirectConfig {

    public static final String HELLO_WORLD_QUEUE_NAME = "queue";

    public final static String DIRECT_NAME = "test-direct";

    /**
     * 1、DirectExchange和Binding两个Bean的配置可以省略掉，
     * 即如果使用DirectExchange，可以只配置一个Queue的实例即可。
     * 2、这个时候使用的其实是默认的直连交换机（DirectExchange），
     * DirectExchange 的路由策略是将消息队列绑定到一个 DirectExchange 上，
     * 当一条消息到达 DirectExchange 时会被转发到与该条消息 routing key 相同的 Queue 上，
     * 例如消息队列名为 “hello-queue”，则 routingkey 为 “hello-queue”
     * 的消息会被该消息队列接收。
     */
    @Bean
    public Queue queue() {
        return new Queue(HELLO_WORLD_QUEUE_NAME);
    }

    /**
     * DirectExchange 的路由策略是将消息队列绑定到一个 DirectExchange 上，
     * 当一条消息到达 DirectExchange 时会被转发到与该条消息 routing key 相同的 Queue 上
     */
    @Bean
    public DirectExchange directExchange() {
        //名字、重启后是否依然有效、以及长期未用时是否删除。
        return new DirectExchange(DIRECT_NAME, true, false);
    }

    /**
     * 创建一个Binding对象将Exchange和Queue绑定在一起
     */
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue())
                .to(directExchange()).with("direct");
    }

}
