package umc.week6.domain.post.domain;

import lombok.Builder;
import lombok.Data;

@Data
public class Post {
    private Long postId;
    private Long memberId;
    private String title;
    private String content;

    @Builder
    public Post(Long postId, Long memberId, String title, String content) {
        this.postId = postId;
        this.memberId = memberId;
        this.title = title;
        this.content = content;
    }
}
