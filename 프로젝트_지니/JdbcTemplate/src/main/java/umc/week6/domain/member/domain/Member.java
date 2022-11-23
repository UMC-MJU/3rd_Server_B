package umc.week6.domain.member.domain;

import lombok.Builder;
import lombok.Data;


@Data
public class Member {
    private Long id;
    private String email;
    private String password;
    private String nickname;

    @Builder
    public Member(Long id, String email, String password, String nickname) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }
}
