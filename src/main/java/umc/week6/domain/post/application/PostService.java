package umc.week6.domain.post.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import umc.week6.domain.post.domain.Post;
import umc.week6.domain.post.domain.PostRepository;
import umc.week6.domain.post.dto.UploadPostReq;
import umc.week6.global.payload.ApiResponse;
import umc.week6.global.payload.Message;

import java.net.URI;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;

    public ResponseEntity<?> uploadPost(UploadPostReq uploadPostReq) {

        Post post = Post.builder()
                .title(uploadPostReq.getTitle())
                .content(uploadPostReq.getContent())
                .anonymous(uploadPostReq.getAnonymous())
                .build();

        postRepository.save(post);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/posts/{id}")
                .buildAndExpand(post.getId()).toUri();

        return ResponseEntity.created(location).body(
                ApiResponse.builder()
                        .check(true)
                        .information(Message.builder().message("게시글이 업로드 되었습니다.").build())
                        .build()
        );
    }
}
