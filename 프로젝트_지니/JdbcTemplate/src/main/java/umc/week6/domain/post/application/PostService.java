package umc.week6.domain.post.application;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import umc.week6.domain.post.domain.PostDao;
import umc.week6.domain.post.dto.UploadPostReq;
import umc.week6.domain.post.dto.UploadPostRes;
import umc.week6.global.DefaultAssert;
import umc.week6.global.payload.ApiResponse;

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
}
