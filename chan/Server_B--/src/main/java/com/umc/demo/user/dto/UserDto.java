package com.umc.demo.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {
    private String password;
    private String name;
    private String profile_img;
    private String text;
}