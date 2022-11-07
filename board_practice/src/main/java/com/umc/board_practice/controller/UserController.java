package com.umc.board_practice.controller;

import com.umc.board_practice.dto.UserDto;
import com.umc.board_practice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/board/user/signup")
    public UserDto signUp(@RequestBody UserDto userDto) {
        return userService.signUp(userDto);
    }

    @GetMapping("/board/user/{password}")
    public UserDto findUserByPassword(@PathVariable String password) {
        return userService.findUserByPassword(password);
    }

    @GetMapping("/board/user")
    public List<UserDto> findAllUser() {
        return userService.findAllUser();
    }

    @PostMapping("/board/user/update/{password}")
    public UserDto updateUserByPassword(@PathVariable String password,
                                        @RequestBody UserDto userDto) {
        return userService.updateUserByPassword(password, userDto);
    }

    @DeleteMapping("/board/user/delete/{password}")
    public void deleteUserByPassword(@PathVariable String password) {
        userService.deleteUserByPassword(password);
    }
}
