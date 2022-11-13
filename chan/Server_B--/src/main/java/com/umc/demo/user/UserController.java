package com.umc.demo.user;

import com.umc.demo.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    // 넘어오는 dto를 valid로 유효성 체크
    public User getPosts(@Valid @RequestBody UserDto userDto) {
        return userService.saveUser(userDto);
    }
}