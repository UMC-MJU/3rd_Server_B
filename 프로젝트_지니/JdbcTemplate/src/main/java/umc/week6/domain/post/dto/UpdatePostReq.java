package umc.week6.domain.post.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class UpdatePostReq {

    private String title;
    private String content;

    @Builder
    public UpdatePostReq(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
