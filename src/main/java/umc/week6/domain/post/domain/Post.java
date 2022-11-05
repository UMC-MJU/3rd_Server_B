package umc.week6.domain.post.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.week6.domain.comment.domain.Comment;
import umc.week6.domain.common.BaseEntity;
import umc.week6.domain.hashtag.domain.PostHashtag;
import umc.week6.domain.member.domain.Member;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    private Boolean anonymous;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<PostHashtag> postHashtags;


    @Builder
    public Post(String title, String content, Boolean anonymous, Member member, List<Comment> comments, List<PostHashtag> postHashtags) {
        this.title = title;
        this.content = content;
        this.anonymous = anonymous;
        this.member = member;
        this.comments = comments;
        this.postHashtags = postHashtags;
    }

}
