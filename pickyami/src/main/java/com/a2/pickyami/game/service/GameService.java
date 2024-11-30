package com.a2.pickyami.game.service;

import com.a2.pickyami.game.entity.GameEntity;
import com.a2.pickyami.game.entity.User;
import com.a2.pickyami.game.enums.GameStatus;
import com.a2.pickyami.game.model.GameStartModel;
import com.a2.pickyami.game.model.GameStartRequest;
import com.a2.pickyami.game.repository.GameRepository;
import com.a2.pickyami.game.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GameService {
    private final GameRepository gameRepository;
    private final UserRepository userRepository;


    public GameStartModel startGame(GameStartRequest request) throws Exception {
        var lastRow = gameRepository.findLastRow();
        var userOptional = userRepository.findByUId(request.getUserUid());
        if (userOptional.isPresent()) {
            var user = userOptional.get();
            GameEntity game;
            if (lastRow.isEmpty()) {
                game = createNewGame(user);
            } else {
                var entity = lastRow.get();
                var players = entity.getPlayers();
                if (players.size() < 4) {
                    players.add(user);
                    game = entity;
                } else {
                    game = createNewGame(user);
                }
            }
            if (game.getPlayers().size() == 4) {
                game.setGameStatus(GameStatus.onGoing);
            }
            gameRepository.save(game);
            return GameStartModel
                    .builder()
                    .status(game.getGameStatus())
                    .playersList(
                            game.getPlayers()
                                    .stream()
                                    .map(e -> GameStartModel.GamePlayers
                                            .builder().profile(e.getProfile())
                                            .name(e.getUsername())
                                            .uid(e.getUid())
                                            .build()
                                    ).toList()
                    ).build();
        } else {
            throw new Exception();
        }
    }

    private GameEntity createNewGame(User user) {
        return GameEntity.builder()
                .gameStatus(GameStatus.onBoarding)
                .gameStartedAt(LocalDateTime.now())
                .players(List.of(user))
                .build();
    }
}
