package com.umc.demo.post.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PagingDto {

    private int post_id;
    private String image;

    private String text;
    //private String createdBy;
    private LocalDateTime createdAt;

    public PagingDto(int post_id, String text, LocalDateTime createdAt) {
        this.post_id = post_id;
        this.text = text;
        this.createdAt = createdAt;
    }
}