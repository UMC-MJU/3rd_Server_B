package umc.week6.domain.post.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umc.week6.domain.post.application.PostService;
import umc.week6.domain.post.domain.Post;
import umc.week6.domain.post.dto.UpdatePostReq;
import umc.week6.domain.post.dto.UploadPostReq;
import umc.week6.domain.post.dto.UploadPostRes;
import umc.week6.global.error.dto.ErrorResponse;
import umc.week6.global.payload.Message;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final PostService postService;

    @Operation(summary = "게시글 업로드", description = "게시글 업로드")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "게시글 업로드 성공", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UploadPostRes.class))}),
            @ApiResponse(responseCode = "400", description = "게시글 업로드 실패", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
    })
    @PostMapping("")
    public ResponseEntity<?> uploadPost(
            @Parameter(description = "UploadPostReq", required = true) @Valid @RequestBody UploadPostReq uploadPostReq) {
        return postService.uploadPost(uploadPostReq);
    }

    @Operation(summary = "게시글 수정", description = "게시글 수정")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "게시글 수정 성공", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Post.class))}),
            @ApiResponse(responseCode = "400", description = "게시글 수정 실패", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
    })
    @PatchMapping("/{id}")
    public ResponseEntity<?> updatePost(
            @Parameter(description = "UpdatePostReq", required = true) @Valid @RequestBody UpdatePostReq updatePostReq,
            @PathVariable Long id
    ) {
        return postService.updatePost(updatePostReq, id);
    }

    @Operation(summary = "아이디로 게시글 조회", description = "아이디로 게시글 조")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "게시글 조회 성공", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Post.class))}),
            @ApiResponse(responseCode = "400", description = "게시글 조회 실패", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getPostById(
            @PathVariable Long id
    ) {
        return postService.findPostById(id);
    }

    @Operation(summary = "아이디로 게시글 삭제", description = "아이디로 게시글 삭제")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "게시글 삭제 성공", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Message.class))}),
            @ApiResponse(responseCode = "400", description = "게시글 삭제 실패", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePostById(
            @PathVariable Long id
    ) {
        return postService.deletePostById(id);
    }

}
