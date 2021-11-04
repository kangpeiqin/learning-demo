package com.example.record.webSocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author KPQ
 * @date 2021/11/4
 */
@ServerEndpoint("/msgEndpoint")
@Component
@Slf4j
public class MessagePushEndPoint extends BaseAbstractEndPoint {

    /**
     * 在线的客户端
     */
    private static Map<String, Session> clients = new ConcurrentHashMap<>();

    @OnOpen
    @Override
    public void onOpen(Session session) {
        //新的客户端连接进来
        clients.put(session.getId(), session);
        log.debug("有新连接加入：{}，当前连接数为：{}", session.getId(), clients.size());
        String message = this.getMessage();
        //向新连接的客户端发送消息
        super.sendMessageSingle(message, session);
    }

    @OnClose
    @Override
    public void onClose(Session session) {
        clients.remove(session.getId());
        log.debug("有新连接关闭：{}，当前连接数为：{}", session.getId(), clients.size());
    }

    @OnMessage
    public void onMessage(String msg) {
        log.info("客户端消息:{}", msg);
        super.sendMessageAll(msg, clients);
    }

    @Override
    public String getMessage() {
        return "msg";
    }

    @Override
    public void broadcastMessage() {
        String message = this.getMessage();
        super.sendMessageAll(message, clients);
    }
}
