package com.umcspring.testserver.controller;

import com.umcspring.testserver.domain.Post;
import com.umcspring.testserver.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    //게시물 전체 조회
    @GetMapping("")
    public List<Post> findAll(){
        return postService.findAll();
    }

    //게시물 단건 조회
    @GetMapping("/{id}")
    public Post findOne(@PathVariable Long id) {
        return postService.findOne(id);
    }

    //게시물 작성
    @PostMapping("")
    public Long posting(@RequestBody Post post) {
        return postService.posting(post, post.getUser().getId());
    }
}
