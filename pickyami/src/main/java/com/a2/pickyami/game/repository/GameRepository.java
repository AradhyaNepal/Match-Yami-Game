package com.a2.pickyami.game.repository;


import com.a2.pickyami.game.entity.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface GameRepository extends JpaRepository<GameEntity, Integer> {
    Optional<GameEntity> findByUid(String uid);


    Optional<GameEntity> findLastRow();
}
