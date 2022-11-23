package com.umc.board_practice.controller;

import com.umc.board_practice.dto.LoginDto;
import com.umc.board_practice.dto.TokenDto;
import com.umc.board_practice.dto.UserDto;
import com.umc.board_practice.jwt.JwtFilter;
import com.umc.board_practice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/board/signup")
    public ResponseEntity<TokenDto> signup(@Valid @RequestBody UserDto userDto) {
        UserDto savedUserDto = userService.signup(userDto);
        LoginDto loginDto = LoginDto.builder()
                .name(savedUserDto.getName())
                .password(savedUserDto.getPassword())
                .build();
        String jwt = userService.login(loginDto);
        return insertTokenInResponse(jwt);
    }

    @PostMapping("/board/login")
    public ResponseEntity<TokenDto> login(@Valid @RequestBody LoginDto loginDto) {
        String jwt = userService.login(loginDto);
        return insertTokenInResponse(jwt);
    }

    private ResponseEntity<TokenDto> insertTokenInResponse(String jwt) {
        //토큰을 Response Header에 넣어줌
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
        //TokenDto를 Response Body에 넣어줌, 그리고 return
        return new ResponseEntity<>(new TokenDto(jwt), httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/board/user")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<UserDto> getMyUserInfo(HttpServletRequest request) {
        return ResponseEntity.ok(userService.getMyUserWithAuthorities());
    }

    @GetMapping("/board/user/{username}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<UserDto> getUserInfo(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserWithAuthorities(username));
    }
}
