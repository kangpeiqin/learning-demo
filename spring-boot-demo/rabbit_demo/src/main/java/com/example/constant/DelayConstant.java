package com.example.constant;

/**
 * @author kpq
 * @date 2024/8/20 16:39
 */
public class DelayConstant {

    /**
     * 消息延时队列，路由到这个队列的消息都有生存时间
     */
    public static final String ORDER_DELAY_QUEUE = "queue.order.delay";

    /**
     * 延时消息就是发送到该交换机的
     */
    public static final String ORDER_DELAY_EXCHANGE = "order.delay.exchange";

    /**
     * 通过这个路由键匹配到死信交换机
     */
    public static final String ORDER_DELAY_ROUTING_KEY = "order.delay";

    /**
     * 死信队列
     */
    public static final String DEAD_ORDER_QUEUE_NAME = "queue.dead.order";


    /**
     * 死信队列交换机 DLX，dead letter发送到的 exchange
     */
    public static final String DEAD_ORDER_EXCHANGE_NAME = "exchange.dead.order";

    /**
     * 死信交换机和死信队列的绑定路由键
     */
    public static final String DEAD_ORDER_ROUTING_KEY = "dead.order";

}
