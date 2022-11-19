package umc.week6.domain.post.application;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import umc.week6.domain.post.domain.Post;
import umc.week6.domain.post.domain.PostDao;
import umc.week6.domain.post.dto.UpdatePostReq;
import umc.week6.domain.post.dto.UploadPostReq;
import umc.week6.domain.post.dto.UploadPostRes;
import umc.week6.global.DefaultAssert;
import umc.week6.global.payload.ApiResponse;
import umc.week6.global.payload.Message;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostDao postDao;

    public ResponseEntity<?> uploadPost(UploadPostReq uploadPostReq) {
        int postId = postDao.createPost(uploadPostReq);

        return ResponseEntity.ok().body(ApiResponse.builder()
                .check(true)
                .information(UploadPostRes.builder()
                        .postId(postId)
                        .userId(uploadPostReq.getUserId())
                        .title(uploadPostReq.getTitle())
                        .content(uploadPostReq.getContent())
                        .build())
                .build());
    }

    public ResponseEntity<?> updatePost(UpdatePostReq updatePostReq, Long id) {
        Post post = postDao.updatePost(updatePostReq, id);

        return ResponseEntity.ok().body(ApiResponse.builder()
                .check(true)
                .information(post)
                .build());
    }

    public ResponseEntity<?> findPostById(Long id) {
        Post post = postDao.findPostById(id);

        return ResponseEntity.ok().body(ApiResponse.builder()
                .check(true)
                .information(post)
                .build());
    }

    public ResponseEntity<?> deletePostById(Long id) {
        postDao.deletePostById(id);

        return ResponseEntity.ok().body(ApiResponse.builder()
                .check(true)
                .information(Message.builder().message("게시물이 삭제되었습니다.").build())
                .build());
    }
}
