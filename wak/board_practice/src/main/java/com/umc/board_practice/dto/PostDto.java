package com.umc.board_practice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostDto {

    private String title;
    private String content;
    private String userName;
}
