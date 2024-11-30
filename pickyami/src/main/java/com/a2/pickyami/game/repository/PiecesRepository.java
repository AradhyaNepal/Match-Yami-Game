package com.a2.pickyami.game.repository;


import com.a2.pickyami.game.entity.Pieces;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PiecesRepository extends JpaRepository<Pieces, Integer> {

    @Query(value = "SELECT * FROM pieces ORDER BY id ASC LIMIT 15", nativeQuery = true)
    List<Pieces> getFirst15Pieces();
}
