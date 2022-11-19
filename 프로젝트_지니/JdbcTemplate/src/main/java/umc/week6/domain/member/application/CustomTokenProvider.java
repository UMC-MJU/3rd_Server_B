package umc.week6.domain.member.application;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import umc.week6.domain.member.domain.Member;
import umc.week6.domain.member.domain.Token;

import java.security.Key;
import java.util.Date;

@Service
public class CustomTokenProvider {

    private final String secretKey = "065AC75741559UCB88F87E5EF897A0FB182ZE2D040AA4CDE06FC6B32D2F859F4";


    public Token createToken(Member member) {

        Date now = new Date();

        int accessTokenExp = 3600000;
        int refreshTokenExp = 1209600000;

        Date accessTokenExpiresIn = new Date(now.getTime() + accessTokenExp);
        Date refreshTokenExpiresIn = new Date(now.getTime() + refreshTokenExp);

        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        Key key = Keys.hmacShaKeyFor(keyBytes);

        String accessToken = Jwts.builder()
                .setSubject(Long.toString(member.getId()))
                .setIssuedAt(new Date())
                .setExpiration(accessTokenExpiresIn)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        String refreshToken = Jwts.builder()
                .setExpiration(refreshTokenExpiresIn)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return Token.builder()
                .userEmail(member.getEmail())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public Token refreshToken(Member member, String refreshToken) {
        Date now = new Date();

        Date accessTokenExpiresIn = new Date(now.getTime() + 3600000);

        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        Key key = Keys.hmacShaKeyFor(keyBytes);

        String accessToken = Jwts.builder()
                .setSubject(Long.toString(member.getId()))
                .setIssuedAt(new Date())
                .setExpiration(accessTokenExpiresIn)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return Token.builder()
                .userEmail(member.getEmail())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }


}
