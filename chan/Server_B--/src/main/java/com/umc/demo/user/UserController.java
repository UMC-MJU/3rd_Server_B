package com.umc.demo.user;

import com.umc.demo.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    // 전체 사용자 조회
    @GetMapping("/all")
    public List<User> getPosts() {
        return userService.getUsers();
    }

    // 이름으로 사용자 조회
    @GetMapping("/{name}")
    public Optional<User> getPosts(@PathVariable String name) {
        return userService.getUserByName(name);
    }

    // 사용자 등록
    @PostMapping()
    public User getPosts(@RequestBody UserDto userDto) {
        return userService.saveUser(userDto);
    }
}