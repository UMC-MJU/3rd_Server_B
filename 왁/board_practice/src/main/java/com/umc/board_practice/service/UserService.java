package com.umc.board_practice.service;

import com.umc.board_practice.domain.Authority;
import com.umc.board_practice.domain.User;
import com.umc.board_practice.dto.LoginDto;
import com.umc.board_practice.dto.TokenDto;
import com.umc.board_practice.dto.UserDto;
import com.umc.board_practice.exception.DuplicateMemberException;
import com.umc.board_practice.exception.NotFoundMemberException;
import com.umc.board_practice.jwt.JwtProvider;
import com.umc.board_practice.repository.UserRepository;
import com.umc.board_practice.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @Transactional
    public UserDto signup(UserDto userDto) {
        if (userRepository.findOneWithAuthoritiesByName(userDto.getName()).orElse(null) != null) {
            throw new DuplicateMemberException("이미 가입되어 있는 유저입니다.");
        }

        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        User newUser = User.createUser(passwordEncode(userDto), authority);
        User savedUser = userRepository.save(newUser);

        return savedUser.toUserDto();
    }

    public String login(LoginDto loginDto) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getName(), loginDto.getPassword());

        //authenticate()가 실행될 때 loadUserByUserName()가 실행된다
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        //생성된 authentication을 context에 저장
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //만들어진 authentication으로 토큰 생성
        return jwtProvider.createToken(authentication);
    }


    private UserDto passwordEncode(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userDto;
    }

    @Transactional(readOnly = true)
    public UserDto getUserWithAuthorities(String username) {
        return userRepository.findOneWithAuthoritiesByName(username)
                .orElseThrow(() -> new NotFoundMemberException("Member not found"))
                .toUserDto();
    }

    @Transactional(readOnly = true)
    public UserDto getMyUserWithAuthorities() {
        return SecurityUtil.getCurrentUsername()
                        .flatMap(userRepository::findOneWithAuthoritiesByName)
                        .orElseThrow(() -> new NotFoundMemberException("Member not found"))
                .toUserDto();
    }


    public void deleteUserByPassword(String password) {
        User findUser = userRepository.findByPassword(password);
        userRepository.delete(findUser);
    }

    public UserDto updateUserByPassword(String password, UserDto userDto) {
        User findUser = userRepository.findByPassword(password);
        User updatedUser = findUser.updateUser(userDto.getName(), userDto.getPassword());
        return UserDto.builder()
                .name(updatedUser.getName())
                .password(updatedUser.getPassword())
                .build();
    }

}
