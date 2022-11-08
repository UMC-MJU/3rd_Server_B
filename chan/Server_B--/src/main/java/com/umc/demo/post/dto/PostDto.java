package com.umc.demo.post.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostDto {
    private int userId;
    private String image;
    private String text;
}