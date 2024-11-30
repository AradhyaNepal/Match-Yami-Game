package com.a2.pickyami.game.repository;


import com.a2.pickyami.game.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface GameRepository extends JpaRepository<Game, Long> {


    @Query("Select g FROM Game g ORDER BY g.id DESC")
    Optional<Game> findLastRow();

    @Query("SELECT g FROM Game g JOIN g.players p WHERE p.uid = :userUid AND g.gameStatus != 'finished' ORDER BY g.id")
    Optional<Game> existingGame(@Param("userUid") String userUid);
}
