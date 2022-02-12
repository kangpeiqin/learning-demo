package com.example.record.webSocket;

import lombok.extern.slf4j.Slf4j;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.Session;
import java.util.Map;

/**
 * @author KPQ
 * @since 1.0
 */
@Slf4j
public abstract class BaseAbstractEndPoint {

    /**
     * 连接建立成功调用的方法
     *
     * @param session
     */
    public abstract void onOpen(Session session);

    /**
     * 连接关闭调用的方法
     *
     * @param session
     */
    public abstract void onClose(Session session);

    /**
     * 发生错误调用的方法
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error(error.getMessage(), error);
    }

    /**
     * 获取需要给前端的消息
     */
    public abstract String getMessage();

    /**
     * 消息广播
     */
    public abstract void broadcastMessage();


    /**
     * 给所有连接的客户端广播消息
     *
     * @param message 消息
     * @param clients 客户端
     */
    protected void sendMessageAll(String message, Map<String, Session> clients) {
        log.debug("发送消息clients={},message={}", clients, message);
        for (Map.Entry<String, Session> sessionEntry : clients.entrySet()) {
            Session toSession = sessionEntry.getValue();
            toSession.getAsyncRemote().sendText(message);

        }
    }

    /**
     * 给指定的客户端推送消息-单播
     *
     * @param message 消息
     * @param client  客户端
     */
    protected void sendMessageSingle(String message, Session client) {
        log.debug("发送消息：client={},message={}", client, message);
        client.getAsyncRemote().sendText(message);
    }
}
