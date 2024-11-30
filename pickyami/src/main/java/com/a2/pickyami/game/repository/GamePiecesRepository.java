package com.a2.pickyami.game.repository;


import com.a2.pickyami.game.entity.GameEntity;
import com.a2.pickyami.game.entity.GamePieces;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface GamePiecesRepository extends JpaRepository<GamePieces, Integer> {
    List<GamePieces> getFirst15Pieces();
}
