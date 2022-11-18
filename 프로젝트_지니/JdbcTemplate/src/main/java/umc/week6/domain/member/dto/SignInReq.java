package umc.week6.domain.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Getter
public class SignInReq {

    @Schema(type = "String", example = "jinpark99@naver.com", description = "이메일")
    @Email
    @NotBlank
    private String email;

    @Schema(type = "String", example = "sejin1234", description = "비밀번호")
    @NotBlank
    private String password;

}
