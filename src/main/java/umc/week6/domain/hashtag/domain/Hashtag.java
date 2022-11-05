package umc.week6.domain.hashtag.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.week6.domain.post.domain.Post;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Hashtag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Builder
    public Hashtag(String name) {
        this.name = name;
    }

}
