package com.a2.pickyami.game.controller;

import com.a2.pickyami.game.config.JwtService;
import com.a2.pickyami.game.config.StompPrincipal;
import com.a2.pickyami.game.model.GameStartModel;
import com.a2.pickyami.game.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class GameController {

    final private GameService service;
    final private SimpMessagingTemplate messagingTemplate;
    final private JwtService jwtService;

    @PostMapping("/start-or-resume")
    public GameStartModel startOrResultGame(@RequestHeader("Authorization") String token) throws Exception {

        var game = service.startOrResumeGame(jwtService.extractUID(token));
        for (var e : game.getPlayersList()) {
            messagingTemplate.convertAndSendToUser(
                    e.getUid(),
                    "/queue/start",
                    game
            );
        }
        return game;

    }

    @MessageMapping("/queue/start")
    protected void start(SimpMessageHeaderAccessor sha, Map message) {
        System.out.println("I was in hello. Yo Yo Bello");
    }

    //client.subscribe('/user/queue/start', callback)


    @MessageMapping("/private-message")
    @SendToUser("/topic/private-message")
    public String getPrivateMessage(String message, StompPrincipal principal) {
        return "Bello";
    }


}
