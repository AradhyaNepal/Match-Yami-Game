package com.a2.pickyami.game.model;
import com.a2.pickyami.game.enums.GameStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameStartModel {
    private  String gameUid;
    private List<GamePlayers> playersList;
    private GameStatus status;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class GamePlayers {
        private  String uid;
        private String name;
        private String profile;
    }
}
