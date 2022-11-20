package com.umc.demo.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findByUserId(int user_id);

    // <Post> findByText(String text, Pageable pageable);

    // JPA 레포지토리에 Paging 함수 선언
    Page<Post> findAll(Pageable pageable);
}