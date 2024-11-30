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

    @RequestMapping("/start-or-resume")
    public void startOrResultGame(@RequestHeader("Authorization") String token) throws Exception {
        String userId = extractUserIdFromToken(token);
        var game = service.startOrResumeGame(userId);
        for (var e : game.getPlayersList()) {
            messagingTemplate.convertAndSendToUser(
                    e.getUid(),
                    "/queue/start",
                    game
            );
        }

    }

    private String extractUserIdFromToken(String token) {
        return "";
    }
}
