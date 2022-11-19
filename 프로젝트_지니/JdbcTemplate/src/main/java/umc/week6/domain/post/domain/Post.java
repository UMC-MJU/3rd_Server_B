package umc.week6.domain.post.domain;

import lombok.Builder;
import lombok.Data;

@Data
public class Post {
    private Long postId;
    private Long userId;
    private String title;
    private String content;

    @Builder
    public Post(Long postId, Long userId, String title, String content) {
        this.postId = postId;
        this.userId = userId;
        this.title = title;
        this.content = content;
    }
}
