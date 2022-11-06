package umc.week6.domain.post.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import umc.week6.domain.comment.domain.Comment;
import umc.week6.domain.comment.dto.CommentRes;

import java.util.List;

@Data
public class PostDetailRes {

    @Schema(type = "String", example = "제목", description = "제목")
    private String title;

    @Schema(type = "String", example = "내용", description = "내용")
    private String content;

    @Schema(type = "Boolean", example = "false", description = "익명 여부")
    private boolean anonymous;

    @Schema(type = "Array", example = "[Comments]", description = "댓글")
    private List<CommentRes> comments;

    @Schema(type = "Array", example = "[취미, 운동]", description = "해시태그")
    private List<String> hashtags;

    @Builder
    public PostDetailRes(String title, String content, boolean anonymous, List<CommentRes> comments, List<String> hashtags) {
        this.title = title;
        this.content = content;
        this.anonymous = anonymous;
        this.comments = comments;
        this.hashtags = hashtags;
    }
}
