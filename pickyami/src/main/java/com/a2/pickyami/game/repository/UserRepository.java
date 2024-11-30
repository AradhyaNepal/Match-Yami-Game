package com.a2.pickyami.game.repository;


import com.a2.pickyami.game.entity.GameEntity;
import com.a2.pickyami.game.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUId(String uid);

}
