package umc.week6.domain.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
public class GetMemberRes {

    @Schema(type = "Long", example = "1", description = "ID")
    private Long id;

    @Schema(type = "String", example = "jinpark99@naver.com", description = "이메일")
    private String email;

    @Schema(type = "String", example = "지니", description = "닉네임")
    private String nickname;

    @Builder
    public GetMemberRes(Long id, String email, String nickname) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
    }
}
