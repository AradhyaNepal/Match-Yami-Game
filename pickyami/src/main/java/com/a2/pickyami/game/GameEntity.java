package com.a2.pickyami.game;

import com.a2.pickyami.game.model.GameStatus;

import java.time.LocalDateTime;
import java.util.List;

public class GameEntity {
    private String id;
    private String gameUid;
    private GameStatus gameStatus;
    private String winnerId;
    private List<String> players;
    private LocalDateTime createdAt;
    private LocalDateTime gameStartedAt;
    private LocalDateTime gameEndedAt;
    private  String remark;


}
