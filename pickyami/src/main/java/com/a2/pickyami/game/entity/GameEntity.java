package com.a2.pickyami.game.entity;



import com.a2.pickyami.game.enums.GameStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class GameEntity {
    private int id;
    private String gameUid;
    private GameStatus gameStatus;
    private String winnerId;
    private List<String> playersUid;
    private LocalDateTime createdAt;
    private LocalDateTime gameStartedAt;
    private LocalDateTime gameEndedAt;
    private  String remark;


}
