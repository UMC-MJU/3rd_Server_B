package umc.week6.domain.member.application;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import umc.week6.domain.member.domain.Member;
import umc.week6.domain.member.domain.Token;

import java.security.Key;
import java.util.Date;

@Service
@Slf4j
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

    public Long getMemberIdFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return Long.parseLong(claims.getSubject());
    }

    public Long getExpiration(String token) {
        Date expiration = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody().getExpiration();
        long now = new Date().getTime();
        return (expiration.getTime() - now);
    }

    public boolean validateToken(String token) {
        try{
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            return true;
        }catch (SecurityException | MalformedJwtException ex){
            log.error("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException ex){
            log.error("만료된 JWT 토큰입니다.");
        }catch (UnsupportedJwtException ex){
            log.error("지원되지 않는 JWT 토큰입니다.");
        }catch (IllegalArgumentException ex){
            log.error("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }


}
