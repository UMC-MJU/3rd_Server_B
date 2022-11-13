package com.umc.board_practice.controller;

import com.umc.board_practice.dto.PostDto;
import com.umc.board_practice.provider.PostProvider;
import com.umc.board_practice.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostProvider postProvider;

    @GetMapping("/board/post/title")
    public PostDto findPostByTitle(@RequestParam(name = "t") String title) {
        return postProvider.findPostByTitle(title);
    }

    @GetMapping("/board/post/name")
    public List<PostDto> findPostByUserName(@RequestParam(name = "n") String name) {
        return postProvider.findPostByUserName(name);
    }

    @GetMapping("/board/post")
    public List<PostDto> findAllPost() {
        return postProvider.findAllPost();
    }

    @PostMapping("/board/post/posting/{name}")
    public PostDto posting(@PathVariable String name,
                           @RequestBody PostDto postDto) {
        return postService.posting(postDto, name);
    }

    @PostMapping("/board/post/update/{title}")
    public PostDto updatePostByTitle(@PathVariable String title,
                                     @RequestBody PostDto postDto) {
        return postService.updatePostByTitle(title, postDto);
    }

    @DeleteMapping("/board/post/delete/{title}/{password}")
    public void deletePostByPasswordAndTitle(@PathVariable String title,
                                             @PathVariable String password) {
        postService.deletePostByPasswordAndTitle(password, title);
    }






}
