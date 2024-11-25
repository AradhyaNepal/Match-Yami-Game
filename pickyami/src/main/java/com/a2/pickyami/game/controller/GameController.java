package com.a2.pickyami.game.controller;


import com.a2.pickyami.game.model.GameStartModel;
import com.a2.pickyami.game.model.GameStartRequest;
import com.a2.pickyami.game.MessageType;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GameController {

    @RequestMapping("/start")
    @SendTo("/topic/start")
    public GameStartModel startGame(@Payload GameStartRequest request) {
        return new GameStartModel(HtmlUtils.htmlEscape(request.getName()),request.getName(), MessageType.JOIN);

    }
}
