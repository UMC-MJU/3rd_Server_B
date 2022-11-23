package com.umcspring.testserver.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    private String title;

    private String text;

    private String img;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") //user_id가 FK
    private User user;


    //==연관관계 편의 메소드==//
    public void setUser(User user) {
        this.user = user;
        user.getPosts().add(this);
    }

    //==생성 메소드==//
    //게시물 작성
    public static Post createPost(User user) {
        Post post = new Post();
        post.setUser(user);
        return post;
    }

    //==비즈니스 로직==//
    //게시물 삭제
    public void deletePost() {

    }
}
