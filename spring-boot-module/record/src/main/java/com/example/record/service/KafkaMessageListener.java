package com.example.record.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author kpq
 * @since 1.0.0
 */
@Component
@Slf4j
public class KafkaMessageListener {

    @KafkaListener(topics = "test", groupId = "test-consumer")
    public void listen(String message) {
        log.info("接收消息: {}", message);
    }
}
