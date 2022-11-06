package umc.week6.domain.comment.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentRes {

    private Long id;

    private String name;

    private String content;

    private boolean anonymous;

    private LocalDateTime createdAt;

    @Builder
    public CommentRes(Long id, String name, String content, boolean anonymous, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.anonymous = anonymous;
        this.createdAt = createdAt;
    }
}
