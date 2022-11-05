package umc.week6.domain.hashtag.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.week6.domain.post.domain.Post;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostHashtag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hashtag_id")
    private Hashtag hashtag;

    @Builder
    public PostHashtag(Post post, Hashtag hashtag) {
        this.post = post;
        this.hashtag = hashtag;
    }

}
