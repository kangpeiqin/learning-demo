package com.example.record.webSocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author KPQ
 * @date 2021/11/4
 */
@Configuration
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter createEndPoint() {
        return new ServerEndpointExporter();
    }
}
