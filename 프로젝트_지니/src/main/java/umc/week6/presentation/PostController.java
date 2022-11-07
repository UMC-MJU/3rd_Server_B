package umc.week6.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umc.week6.domain.post.application.PostService;
import umc.week6.domain.post.dto.UpdatePostReq;
import umc.week6.domain.post.dto.UploadPostReq;
import umc.week6.global.error.dto.ErrorResponse;

import javax.validation.Valid;

@Tag(name = "Post", description = "Post API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @Operation(summary = "게시글 만들기", description = "게시글 만들기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "게시글 만들기 성공", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = umc.week6.global.payload.ApiResponse.class))}),
            @ApiResponse(responseCode = "400", description = "게시글 만들기 실패", content = {@Content(mediaType = "application", schema = @Schema(implementation = ErrorResponse.class))})
    })
    @PostMapping("/upload")
    public ResponseEntity<?> uploadPost(
            @Parameter(description = "UploadPostReq", required = true)@Valid @RequestBody UploadPostReq uploadPostReq
            ) {
        return postService.uploadPost(uploadPostReq);
    }

    @Operation(summary = "게시글 조회", description = "게시글 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "게시글 조회 성공", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = umc.week6.global.payload.ApiResponse.class))}),
            @ApiResponse(responseCode = "400", description = "게시글 조회 실패", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getPost(
            @PathVariable Long id
    ) {
        return postService.getPost(id);
    }

    @Operation(summary = "게시글 수정", description = "게시글 수정")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "게시글 수정 성공", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = umc.week6.global.payload.ApiResponse.class))}),
            @ApiResponse(responseCode = "400", description = "게시글 수정 실패", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
    })
    @PatchMapping("/{id}")
    public ResponseEntity<?> updatePost(
            @Parameter(description = "UpdatePostReq", required = true)@Valid @RequestBody UpdatePostReq updatePostReq,
            @PathVariable Long id
            ) {
        return postService.updatePost(updatePostReq, id);
    }


}
