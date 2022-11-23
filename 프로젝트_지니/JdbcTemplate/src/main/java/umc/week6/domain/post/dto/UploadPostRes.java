package umc.week6.domain.post.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class UploadPostRes {

    private int postId;
    private int userId;
    private String title;
    private String content;

    @Builder
    public UploadPostRes(int postId, int userId, String title, String content) {
        this.postId = postId;
        this.userId = userId;
        this.title = title;
        this.content = content;
    }
}
