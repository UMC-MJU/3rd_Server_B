package com.umc.demo.post;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
//@MappedSuperclass // 왜 시간 안들어감??
@EntityListeners(AuditingEntityListener.class)
@RequiredArgsConstructor
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int post_id;

    // 컬럼 명에 언더바 있으면 data jpa findBy*** 인식 안됨
    // 언더바 쓰고싶으면 이런식으로 해야 함.
    // https://sunpil.tistory.com/302
    @Column(name = "user_id")
    private int userId;

    private String image;

    private String text;

    @CreatedDate
//    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Builder
    public Post(int post_id, int userId, String image, String text, LocalDateTime createdAt) {
        this.post_id = post_id;
        this.userId = userId;
        this.image = image;
        this.text = text;
        this.createdAt = createdAt;
    }
}