package umc.week6.domain.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class SignUpReq {

    @Schema(type = "String", example = "jinpark99@naver.com", description = "이메일")
    @NotBlank
    @Email
    private String email;

    @Schema(type = "String", example = "박세진", description = "이름")
    @NotBlank
    private String name;

    @Schema(type = "String", example = "sejin1234", description = "비밀번호")
    @NotBlank
    private String password;

}
