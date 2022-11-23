package umc.week6.global.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import umc.week6.domain.member.application.CustomTokenProvider;
import umc.week6.domain.member.domain.Member;
import umc.week6.domain.member.domain.MemberDao;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomOncePerRequestFilter extends OncePerRequestFilter {

    @Autowired
    private CustomTokenProvider customTokenProvider;

    @Autowired
    private MemberDao memberDao;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = getJwtFromRequest(request);

        if (StringUtils.hasText(jwt) && customTokenProvider.validateToken(jwt)) {
            Member member = memberDao.findMemberById(customTokenProvider.getMemberIdFromToken(jwt));
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(member.getEmail(), member.getPassword()));
        }

        filterChain.doFilter(request, response);

    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }
}
