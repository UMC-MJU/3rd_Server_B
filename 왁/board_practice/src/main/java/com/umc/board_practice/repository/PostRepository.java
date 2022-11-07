package com.umc.board_practice.repository;


import com.umc.board_practice.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    Post findByTitle(String title);
    List<Post> findAllByUserName(String name);
}
