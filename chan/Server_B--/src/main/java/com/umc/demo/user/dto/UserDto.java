package com.umc.demo.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

// https://velog.io/@jyleedev/유효성검사
// spring boot 2.3 이상부터는 모듈로 빠져 validation 의존성을 따로 추가해야 한다.
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class UserDto {

    @NotBlank(message = "이메일은 필수 입력 값입니다.") // null을 허용하지 않음, 어도 white-space가 아닌 문자가 한개 이상 포함되어야 함
    @Email(message = "이메일 형식에 맞지 않습니다.") // 이메일 양식이어야 함
    private String email;

    private String password;
    private String name;
    private String profile_img;
    private String text;
}