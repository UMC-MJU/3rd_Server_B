package umc.week6.domain.member.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.week6.domain.common.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String name;

    private String imageUrl;

    @JsonIgnore
    private String password;

    @Builder
    public Member(String email, String name, String imageUrl, String password) {
        this.email = email;
        this.name = name;
        this.imageUrl = imageUrl;
        this.password = password;
    }


}
