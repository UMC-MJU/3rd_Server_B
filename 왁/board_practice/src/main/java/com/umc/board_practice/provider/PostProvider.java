package com.umc.board_practice.provider;

import com.umc.board_practice.domain.Post;
import com.umc.board_practice.dto.PostDto;
import com.umc.board_practice.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostProvider {

    private final PostRepository postRepository;

    public PostDto findPostByTitle (String title) {
        Post findPost = postRepository.findByTitle(title);
        return PostDto.builder()
                .content(findPost.getContent())
                .title(findPost.getTitle())
                .userName(findPost.getUser().getName())
                .build();
    }

    public List<PostDto> findPostByUserName (String name) {
        List<Post> findPosts = postRepository.findAllByUserName(name);
        return findPosts.stream()
                .map(Post::toPostDto)
                .collect(Collectors.toList());
    }

    public List<PostDto> findAllPost() {
        List<Post> findPosts = postRepository.findAll();
        return findPosts.stream()
                .map(Post::toPostDto)
                .collect(Collectors.toList());
    }
}
