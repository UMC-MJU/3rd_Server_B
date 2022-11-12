package umc.week6.domain.post.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class UpdatePostReq {

    @Schema(type = "String", example = "수정 제목", description = "수정 제목")
    @NotBlank
    private String title;

    @Schema(type = "String", example = "수정 내용", description = "수정 내용")
    @NotBlank
    private String content;

    @Schema(type = "Boolean", example = "false", description = "수정 익명 여부")
    @NotNull
    private boolean anonymous;

    @Schema(type = "Array", example = "[오늘의 패션, OOTD]", description = "수정 해쉬태그")
    private List<String> hashtags;

}
