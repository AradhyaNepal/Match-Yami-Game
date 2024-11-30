package com.a2.pickyami.game.repository;
import com.a2.pickyami.game.entity.Players;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PlayerRepository extends JpaRepository<Players, Integer> {
    @Query("SELECT p FROM Players p WHERE p.uid = :uid")
    Optional<Players> findByUId(@Param("uid") String uid);

    @Query("SELECT p FROM Players p WHERE p.email = :email")
    Optional<Players> findByEmail(@Param("email")String email);

}
