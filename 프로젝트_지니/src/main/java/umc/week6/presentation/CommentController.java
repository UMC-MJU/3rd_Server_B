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
import umc.week6.domain.comment.application.CommentService;
import umc.week6.domain.comment.dto.AddCommentReq;
import umc.week6.global.error.dto.ErrorResponse;

import javax.validation.Valid;

@Tag(name = "Comment", description = "Comment API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts/{id}")
public class CommentController {

    private final CommentService commentService;

    @Operation(summary = "댓글 등록", description = "댓글 등록")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "댓글 등록 성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = umc.week6.global.payload.ApiResponse.class))),
            @ApiResponse(responseCode = "400", description = "댓글 등록 실패", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("")
    public ResponseEntity<?> addComment(
            @Parameter(description = "AddCommentReq", required = true) @Valid @RequestBody AddCommentReq addCommentReq,
            @PathVariable Long id
    ) {

        return commentService.addComment(id, addCommentReq);
    }
}
