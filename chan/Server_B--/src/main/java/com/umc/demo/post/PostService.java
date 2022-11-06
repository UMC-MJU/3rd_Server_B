package com.umc.demo.post;

import com.umc.demo.post.dto.PostDto;
import com.umc.demo.post.dto.UpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class PostService {

    private final PostRepository postRepository;

    public Optional<Post> getPostById(int post_id) {
        return postRepository.findById(post_id);
    }

    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    public List<Post> getUsersPost(int user_id) {
        return postRepository.findByUserId(user_id);
    }

    public Post postPost(PostDto postDto) {

        Post post = Post.builder()
                .userId(postDto.getUserId())
                .image(postDto.getImage())
                .text(postDto.getText())
                .build();

        // 똑같은 결과
//        Post post2 = new Post();
//        post2.setUserId(postDto.getUserId());
//        post2.setImage(postDto.getImage());
//        post2.setText(postDto.getText());
        return postRepository.save(post);
    }

    public Optional<Post> updatePost(int post_id, UpdateDto updateDto) {
        Optional<Post> post = postRepository.findById(post_id);
        post.ifPresent(selectedPost -> {
            selectedPost.setText(updateDto.getText());
            selectedPost.setImage(updateDto.getImage());
            postRepository.save(selectedPost);
        });
        return post;
    }

    public void deletePost(int post_id) {
        postRepository.deleteById(post_id);
    }
}