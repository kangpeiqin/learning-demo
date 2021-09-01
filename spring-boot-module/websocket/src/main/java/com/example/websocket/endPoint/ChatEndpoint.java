package com.example.websocket.endPoint;

import com.example.websocket.model.Message;
import com.example.websocket.coder.MessageDecoder;
import com.example.websocket.coder.MessageEncoder;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author KPQ
 * @date 2021/9/1
 */
@ServerEndpoint(value = "/chat/{username}", decoders = MessageDecoder.class, encoders = MessageEncoder.class)
@Component
@EqualsAndHashCode
@Slf4j
public class ChatEndpoint {

    private static Session globalSession;
    private static final Set<ChatEndpoint> chatEndpoints = new CopyOnWriteArraySet<>();
    private static HashMap<String, String> users = new HashMap<>();

    /**
     * 客户端打开WebSocket服务端点调用方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) {
        globalSession = session;
        chatEndpoints.add(this);
        users.put(session.getId(), username);
        Message message = new Message();
        message.setFrom(username);
        message.setContent("Connected!");
        broadcast(message);
    }

    @OnMessage
    public void onMessage(Session session, Message message) {
        message.setFrom(users.get(session.getId()));
        broadcast(message);
    }

    @OnClose
    public void onClose(Session session) {
        chatEndpoints.remove(this);
        Message message = new Message();
        message.setFrom(users.get(session.getId()));
        message.setContent("Disconnected!");
        broadcast(message);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        // Do error handling here
    }

    private static void broadcast(Message message) {
        chatEndpoints.forEach(endpoint -> {
            synchronized (ChatEndpoint.class) {
                try {
                    globalSession.getBasicRemote()
                            .sendObject(message);
                } catch (IOException | EncodeException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
