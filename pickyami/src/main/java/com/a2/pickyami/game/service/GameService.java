package com.a2.pickyami.game.service;

import com.a2.pickyami.game.entity.GameEntity;
import com.a2.pickyami.game.enums.GameStatus;
import com.a2.pickyami.game.model.GameStartModel;
import com.a2.pickyami.game.model.GameStartRequest;
import com.a2.pickyami.game.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GameService {
    private final GameRepository repository;


    public GameStartModel startGame(GameStartRequest request) {
        var lastRow = repository.findLastRow();
        GameEntity game;
        if (lastRow.isEmpty()) {
            game = createNewGame(request);
        } else {
            var entity = lastRow.get();
            var players = entity.getPlayersUid();
            if (players.size() < 4) {
                players.add(request.getUserUid());
                entity.setPlayersUid(players);
                game = entity;
            } else {
                game = createNewGame(request);
            }
        }
        if (game.getPlayersUid().size() == 4) {
            game.setGameStatus(GameStatus.onGoing);
        }
        return GameStartModel.builder().build();
    }

    private GameEntity createNewGame(GameStartRequest request) {

        return GameEntity.builder()
                .gameStatus(GameStatus.onBoarding)
                .gameStartedAt(LocalDateTime.now())
                .playersUid(List.of(request.getUserUid()))
                .build();

    }
}
