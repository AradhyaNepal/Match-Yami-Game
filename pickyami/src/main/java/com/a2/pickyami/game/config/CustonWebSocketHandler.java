package com.a2.pickyami.game.config;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

class CustonWebSocketHandler implements WebSocketHandler {
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("Session Id" + session.getId());

    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        System.out.println("On message");
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        System.out.println("Exception" + exception.toString());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        System.out.println("Closed");
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
    // Custom class for storing principal
//    @Override
//    protected Principal determineUser(
//            ServerHttpRequest request,
//            WebSocketHandler wsHandler,
//            Map<String, Object> attributes
//    ) {
//        var user=UUID.randomUUID().toString();
//        System.out.println("The user is "+user);
//        return new StompPrincipal(user);
//    }


    //return UserPrinciple
}
