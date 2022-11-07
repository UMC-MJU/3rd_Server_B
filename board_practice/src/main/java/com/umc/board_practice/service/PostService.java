package com.umc.board_practice.service;

import com.umc.board_practice.domain.Post;
import com.umc.board_practice.domain.User;
import com.umc.board_practice.dto.PostDto;
import com.umc.board_practice.repository.PostRepository;
import com.umc.board_practice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public PostDto posting (PostDto postDto, String name) {
        User findUser = userRepository.findByName(name);
        Post newPost = Post.createPost(postDto, findUser);
        Post savedPost = postRepository.save(newPost);
        return PostDto.builder()
                .content(savedPost.getContent())
                .title(savedPost.getTitle())
                .userName(savedPost.getUser().getName())
                .build();
    }

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

    @Transactional
    public PostDto updatePostByTitle(String title, PostDto postDto) {
        Post findPost = postRepository.findByTitle(title);
        findPost.setTitle(postDto.getTitle());
        findPost.setContent(postDto.getContent());
        return PostDto.builder()
                .content(findPost.getContent())
                .title(findPost.getTitle())
                .userName(findPost.getUser().getName())
                .build();
    }

    @Transactional
    public void deletePostByPasswordAndTitle(String password, String title) {
        User findUser = userRepository.findByPassword(password);
        Post findPost = postRepository.findByTitle(title);

        if (findPost.getUser().getName().equals(findUser.getName())) {
            postRepository.delete(findPost);
        } else {
            throw new IllegalStateException("권한이 없거나, 게시물의 제목을 확인해주세요");
        }
    }


}
