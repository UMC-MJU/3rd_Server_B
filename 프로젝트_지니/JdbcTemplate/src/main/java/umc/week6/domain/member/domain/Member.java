package umc.week6.domain.member.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Member {
    private Long id;
    private String email;
    private String password;
    private String nickname;
}
