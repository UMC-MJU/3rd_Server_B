package umc.week6.domain.member.domain;

import lombok.Builder;
import lombok.Data;

@Data
public class Token {


    private String userEmail;
    private String accessToken;
    private String refreshToken;

    public Token updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }

    @Builder
    public Token(String userEmail, String accessToken, String refreshToken) {
        this.userEmail = userEmail;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

}
