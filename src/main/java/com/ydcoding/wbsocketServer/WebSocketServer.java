package com.ydcoding.wbsocketServer;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @program: honeysalesplatform
 * @description
 * @author: ydcoding
 * @create: 2019-05-04 10:06
 **/
@Component
@ServerEndpoint(value = "/webSocketServer")//标记此类为服务端
public class WebSocketServer {

    private Session session;

    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<>();
    //当客户端打开连接：1.添加会话对象
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(this);
        System.out.println("【websocket消息】有新的连接, 总数:{}"+ webSocketSet.size());

    }

    @OnClose
    public void onClose() {
        webSocketSet.remove(this);

    }

    @OnMessage
    public void onMessage(String message) {

    }
//遍历消息，并将消息发送给客户端
    public void sendMessage(String message) {
        for (WebSocketServer webSocket: webSocketSet) {

            try {
                webSocket.session.getBasicRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
