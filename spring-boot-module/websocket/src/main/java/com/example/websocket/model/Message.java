package com.example.websocket.model;

import lombok.Data;

/**
 * @author KPQ
 * @date 2021/9/1
 */
@Data
public class Message {

    private String from;

    private String to;

    private String content;

}
