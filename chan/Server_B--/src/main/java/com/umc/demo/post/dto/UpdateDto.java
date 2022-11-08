package com.umc.demo.post.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateDto {
    private String image;
    private String text;
}
