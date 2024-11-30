package com.a2.pickyami.game.entity;


import com.a2.pickyami.game.enums.GameStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
@Data
public class GameEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false, updatable = false)
    private String gameUid;
    private GameStatus gameStatus;
    @OneToMany(mappedBy = "winner", cascade = CascadeType.ALL)
    private User winner;
    @ManyToMany(mappedBy = "players", cascade = CascadeType.ALL)
    private List<User> players;
    private LocalDateTime createdAt;
    private LocalDateTime gameStartedAt;
    private LocalDateTime gameEndedAt;
    private String remark;

    @PrePersist
    public void generateGameUid() {
        if (gameUid == null) {
            gameUid = UUID.randomUUID().toString();
        }
    }


}
