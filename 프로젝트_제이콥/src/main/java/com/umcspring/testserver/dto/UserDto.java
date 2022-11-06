package com.umcspring.testserver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor   //@AllArgsConstructor => 모든 필드 값을 파라미터로 받는 생성자를 만듦.
public class UserDto {
    private int idx;
    private String name;
    private String id;
    private String email;
    private String password;
}
