package com.umc.demo.post;

import com.umc.demo.config.BaseException;
import com.umc.demo.post.dto.PagingDto;
import com.umc.demo.post.dto.PostDto;
import com.umc.demo.post.dto.UpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.umc.demo.config.BaseResponseStatus.DATABASE_ERROR;

@RequiredArgsConstructor
@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;
    @Autowired
    PostRepository postRepository;

    // 해당 유저 전체 포스트 조회
    @GetMapping("/all")
    public List<Post> getPosts() {
        return postService.getPosts();
    }

    // id로 포스트 하나 조회
    @GetMapping("/{post_id}")
    public Optional<Post> getPostById(@PathVariable int post_id) throws BaseException {
        try {
            return postService.getPostById(post_id);
        } catch (BaseException exception) {
            throw new BaseException(DATABASE_ERROR);
        }
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

//    @GetMapping("/posts/search")
//    public String search(String text, @PageableDefault(sort = "post_id", direction = Sort.Direction.DESC) Pageable pageable) {
//        Page<Post> searchList = postService.search(text, pageable);
//
//        return String.valueOf(searchList);
//    }

    // https://semtax.tistory.com/77
    // 페이징
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/post/page")
    public Page<PagingDto> paging(@PageableDefault(size=5, sort="createdAt") Pageable pageRequest) {
        // createdAt 기준으로 정렬
        // 예시) http://localhost:8080/post/post/page?page=1&size=4 (size 안쓰면 디폴트는 5)
        // 서비스로 빼는 것이 좋지만 연습용..
        Page<Post> postList = postRepository.findAll(pageRequest);

        Page<PagingDto> pagingList = postList.map(
                post -> new PagingDto(
                        post.getPost_id(),post.getText(),
                        post.getCreatedAt()
                ));

        return pagingList;
    }
}