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
@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false, updatable = false)
    private String gameUid;
    @Enumerated(EnumType.STRING)
    private GameStatus gameStatus;
    @ManyToOne
    @JoinColumn(name = "winner_id")
    private Players winner;
    @ManyToMany
    @JoinTable(
            name = "game_players",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<Players> players;
    private LocalDateTime createdAt;
    private LocalDateTime gameStartedAt;
    private LocalDateTime gameEndedAt;
    private String remark;
    @ManyToMany
    @JoinTable(
            name = "game_pieces",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "pieces_id")
    )
    private List<Pieces> gamePieces;

    @PrePersist
    public void generateGameUid() {
        if (gameUid == null) {
            gameUid = UUID.randomUUID().toString();
        }
    }


}
