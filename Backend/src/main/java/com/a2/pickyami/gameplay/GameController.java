package com.a2.pickyami.gameplay;

import com.a2.pickyami.gameplay.model.GameStartModel;
import com.a2.pickyami.gameplay.model.GameStartRequest;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GameController {

    @RequestMapping("/start")
    @SendTo("/topic/start")
    public GameStartModel startGame(@Payload GameStartRequest request) {
        return new GameStartModel(HtmlUtils.htmlEscape(request.getName()),request.getName());

    }

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public GameStartModel startGame2(@Payload GameStartModel request) {
        return request;

    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public GameStartModel addUser(@Payload GameStartModel request, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", request.getSender());
        return request;
    }
}
