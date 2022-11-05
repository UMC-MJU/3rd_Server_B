package com.umc.board_practice.controller;

import com.umc.board_practice.dto.PostDto;
import com.umc.board_practice.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/board/post/{title}")
    public PostDto findPostByTitle(@PathVariable String title) {
        return postService.findPostByTitle(title);
    }

    @GetMapping("/board/post/{userName}")
    public List<PostDto> findPostByUserName(@PathVariable String userName) {
        return postService.findPostByUserName(userName);
    }

    @GetMapping("/board/post")
    public List<PostDto> findAllPost() {
        return postService.findAllPost();
    }

    @PostMapping("/board/post/posting/{userName}")
    public PostDto posting(@PathVariable String userName,
                           @RequestBody PostDto postDto) {
        return postService.posting(postDto, userName);
    }

    @PostMapping("/board/post/update/{title}")
    public PostDto updatePostByTitle(@PathVariable String title,
                                     PostDto postDto) {
        return postService.updatePostByTitle(title, postDto);
    }

    @DeleteMapping("/board/post/delete/{title}/{password}")
    public void deletePostByPasswordAndTitle(@PathVariable String title,
                                             @PathVariable String password) {
        postService.deletePostByPasswordAndTitle(password, title);
    }






}
