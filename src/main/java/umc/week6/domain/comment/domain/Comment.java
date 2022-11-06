package umc.week6.domain.comment.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.week6.domain.member.domain.Member;
import umc.week6.domain.post.domain.Post;
import umc.week6.domain.common.BaseEntity;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = false)
    private String content;

    private boolean anonymous;

    @Builder
    public Comment(String content, Post post, Member member, boolean anonymous) {
        this.content = content;
        this.post = post;
        this.member = member;
        this.anonymous = anonymous;
    }

}
