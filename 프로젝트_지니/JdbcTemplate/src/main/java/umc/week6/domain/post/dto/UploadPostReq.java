package umc.week6.domain.post.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UploadPostReq {

    private int userId;

    @NotNull
    private String title;

    @NotNull
    private String content;
}
