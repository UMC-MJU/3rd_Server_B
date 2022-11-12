package umc.week6.domain.comment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AddCommentReq {

    @Schema(type = "String", example = "댓글", description = "댓글")
    @NotNull
    private String content;

    @Schema(type = "Boolean", example = "true", description = "익명 여부")
    private boolean anonymous;

    @Schema(type = "String", example = "jinpark99@naver.com", description = "댓글 주인")
    private String email;

}
