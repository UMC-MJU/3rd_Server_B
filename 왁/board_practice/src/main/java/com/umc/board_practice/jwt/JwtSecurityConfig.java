package com.umc.board_practice.jwt;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final JwtProvider jwtProvider;

    public JwtSecurityConfig(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    //JwtFilter를 Security로직에 등록
    @Override
    public void configure(HttpSecurity http) {
        http.addFilterBefore(
                new JwtFilter(jwtProvider),
                UsernamePasswordAuthenticationFilter.class
        );
    }
}