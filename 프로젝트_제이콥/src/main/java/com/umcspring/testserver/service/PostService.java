package com.umcspring.testserver.service;

import com.umcspring.testserver.domain.Post;
import com.umcspring.testserver.domain.User;
import com.umcspring.testserver.repository.PostRepository;
import com.umcspring.testserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    //게시물 작성
    @Transactional
    public Long posting(Post post, Long userId){
        //엔티티 조회
        User user = userRepository.findOne(userId);

        post.setUser(user);
        //게시물 저장
        postRepository.save(post);
        System.out.println(post.getTitle());
        return post.getId();
    }

    //게시물 전체 조회
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    //게시물 단건 조회
    public Post findOne(Long postId) {
        return postRepository.findOne(postId);
    }

    //게시물 삭제
    @Transactional
    public void deletePost(Long postId) {
        //게시물 엔티티 조회
        Post post = postRepository.findOne(postId);
        //게시물 삭제

    }
}
