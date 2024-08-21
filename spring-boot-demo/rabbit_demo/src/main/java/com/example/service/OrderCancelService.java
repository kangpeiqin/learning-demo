package com.example.service;

import com.example.constant.DelayConstant;
import com.example.enums.OrderStatusEnum;
import com.example.enums.PayStatusEnum;
import com.example.model.Order;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

/**
 * @author zzz
 * @date 2024/8/20 17:31
 */
@Component
@Slf4j
public class OrderCancelService {

    @RabbitListener(queues = {DelayConstant.DEAD_ORDER_QUEUE_NAME})
    public void orderDelayQueue(Order order, Message message, Channel channel) {
        log.info("========================消费时间：{}============================================", new Date());
        try {
            log.info("【orderDelayQueue 监听的消息】 - 【消费时间】 - [{}]- 【订单内容】 - [{}]", new Date(), order.toString());
            /// 一：下单逻辑
            //1、（商户后台系统-第三方支付系统）下单时，创建商户系统订单，并请求第三方（微信、支付宝）统一下单API(返回支付链接，这时交易状态为未支付)
            //2、（用户-第三方支付系统）根据支付链接像微信、支付宝发起支付请求，支付完成后会根据配置的链接进行回调
            //3、前端、客户端在完成支付的时候主动通过后端接口发起订单查询（后端查询订单再第三方系统是否完成支付）
            /// 二：订单超时自动取消逻辑，保证消费的幂等性
            // 1、通过第三方支付系统提供的订单查询功能，查询订单的支付状态，如果为未支付，则取消订单，更新商户系统订单状态
            // 2、调用第三方系统的API，取消第三方系统的订单
            if (PayStatusEnum.UNPAID.getStatus().equals(order.getOrderStatus())) {
                order.setOrderStatus(OrderStatusEnum.CANCEL.getStatus());
                log.info("【该订单未支付，取消订单】,下单时间：{},订单名称：{}", order.getOrderTime(), order.getName());
            }
            // 手动确认消息处理完成
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);

        } catch (Exception e) {
            log.error("处理消息失败，消息重新入队", e);
            try {
                // 手动拒绝消息，并将消息重新入队
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            } catch (IOException ioException) {
                log.error("重新入队消息失败", ioException);
            }
        }
        log.info("========================消费完成============================================");
    }

}
