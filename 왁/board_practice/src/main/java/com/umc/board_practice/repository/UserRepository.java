package com.umc.board_practice.repository;

import com.umc.board_practice.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);
    User findByPassword(String password);
    @EntityGraph(attributePaths = "authorities")//EAGER 조회
    Optional<User> findOneWithAuthoritiesByName(String name);
}
