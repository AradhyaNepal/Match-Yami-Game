package com.a2.pickyami.game.controller;

import com.a2.pickyami.game.config.JwtService;
import com.a2.pickyami.game.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GameController {

    final private GameService service;
    final private SimpMessagingTemplate messagingTemplate;
    final private JwtService jwtService;

    @PostMapping("/start-or-resume")
    public void startOrResultGame(@RequestHeader("Authorization") String token) throws Exception {

        var game = service.startOrResumeGame(jwtService.extractUID(token));
        for (var e : game.getPlayersList()) {
            messagingTemplate.convertAndSendToUser(
                    e.getUid(),
                    "/queue/start",
                    game
            );
        }

    }


}
