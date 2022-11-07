package com.umc.board_practice.domain;

import com.umc.board_practice.dto.PostDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Post {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    //연관관계 메소드
    public void setUser(User user) {
        this.user = user;
        user.getPosts().add(this);
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static Post createPost(PostDto postDto, User user) {
        Post post = new Post();
        post.setContent(postDto.getContent());
        post.setTitle(postDto.getTitle());
        post.setUser(user);
        return post;
    }

    public PostDto toPostDto() {
        return PostDto.builder()
                .title(this.getTitle())
                .content(this.getContent())
                .userName(this.getUser().getName())
                .build();
    }

}
