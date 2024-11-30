package com.a2.pickyami.game.controller;

import com.a2.pickyami.game.model.GameStartModel;
import com.a2.pickyami.game.model.GameStartRequest;
import com.a2.pickyami.game.service.GameService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class GameController {

    final private GameService service;
    private final SimpMessagingTemplate messagingTemplate;

    @RequestMapping("/start")
    public void startGame(@RequestHeader("Authorization") String token, @RequestBody @Valid GameStartRequest request) {
        String userId = extractUserIdFromToken(token);
        var game = service.startGame(request);
        messagingTemplate.convertAndSendToUser(
                userId,
                "/queue/start",
                game
        );
    }

    private String extractUserIdFromToken(String token) {
        return "";
    }
}
