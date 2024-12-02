package com.a2.pickyami.game.service;

import com.a2.pickyami.game.entity.Game;
import com.a2.pickyami.game.entity.Players;
import com.a2.pickyami.game.enums.GameStatus;
import com.a2.pickyami.game.model.GameStartModel;
import com.a2.pickyami.game.repository.PiecesRepository;
import com.a2.pickyami.game.repository.GameRepository;
import com.a2.pickyami.game.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GameService {
    private final GameRepository gameRepository;
    private final PiecesRepository gamePiecesRepository;
    private final PlayerRepository userRepository;


    public GameStartModel startOrResumeGame(String uid) throws Exception {
        var userOptional = userRepository.findByUId(uid);
        var exitingGame = gameRepository.existingGame(uid);
        if (exitingGame.isPresent()) {
            return gameToResponse(exitingGame.get());
        }
        var lastRow = gameRepository.findLastRow();
        if (userOptional.isPresent()) {
            var user = userOptional.get();
            Game game;
            if (lastRow.isEmpty()) {
                game = createNewGame(user);
            } else {
                var entity = lastRow.get();
                var players = entity.getPlayers();
                if (players.size() < 2) {
                    players.add(user);
                    game = entity;
                } else {
                    game = createNewGame(user);
                }
            }
            if (game.getPlayers().size() == 2) {
                game.setGameStatus(GameStatus.onGoing);
                var halfPieces = gamePiecesRepository.getFirst15Pieces();
                var pieces = new ArrayList<>(halfPieces);
                pieces.addAll(halfPieces);
                Collections.shuffle(pieces);
                game.setGamePieces(pieces);
            }
            gameRepository.save(game);
            return gameToResponse(game);
        } else {
            throw new Exception();
        }
    }

    private static GameStartModel gameToResponse(Game game) {
        return GameStartModel
                .builder()
                .gameUid(game.getGameUid())
                .status(game.getGameStatus())
                .playersList(
                        game.getPlayers()
                                .stream()
                                .map(e -> GameStartModel.GamePlayers
                                        .builder().profile(e.getProfile())
                                        .name(e.getFullName())
                                        .uid(e.getUid())
                                        .build()
                                ).toList()
                ).build();
    }

    private Game createNewGame(Players user) {
        return Game.builder()
                .gameStatus(GameStatus.onBoarding)
                .gameStartedAt(LocalDateTime.now())
                .players(List.of(user))
                .build();
    }
}
