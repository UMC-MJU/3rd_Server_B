package com.umc.board_practice.repository;

import com.umc.board_practice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);
    User findByPassword(String password);
}
