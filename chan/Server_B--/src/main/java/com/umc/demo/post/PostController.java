package com.umc.demo.post;

import com.umc.demo.post.dto.PostDto;
import com.umc.demo.post.dto.UpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    // 해당 유저 전체 포스트 조회
    @GetMapping("/all")
    public List<Post> getPosts() {
        return postService.getPosts();
    }

    // id로 포스트 하나 조회
    @GetMapping("/{post_id}")
    public Optional<Post> getPostById(@PathVariable int post_id) {
        return postService.getPostById(post_id);
    }

    // 해당 유저의 모든 포스트 조회
    @GetMapping()
    public List<Post> getUsersPost(@RequestParam("user_id") int user_id) {
        return postService.getUsersPost(user_id);
    }

    // 포스트 업로드
    // requestbody는 여러개 쓸 수 없다!!!!! 하나만 - dto로 받기
    // postman form-data로 하면 오류 왜??(Content-Disposition) raw로 하면 잘됨.
    @PostMapping()
    public Post postPost(@RequestBody PostDto postDto) {
        return postService.postPost(postDto);
    }

    // 포스트 업데이트
    @PatchMapping("/{post_id}")
    public Optional<Post> updatePost(@PathVariable int post_id, @RequestBody UpdateDto updateDto) {
        return postService.updatePost(post_id, updateDto);
    }

    // 포스트 삭제
    @DeleteMapping("/{post_id}")
    public void updatePost(@PathVariable int post_id) {
        postService.deletePost(post_id);
    }
}