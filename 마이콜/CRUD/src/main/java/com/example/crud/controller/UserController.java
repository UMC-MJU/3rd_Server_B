package com.example.crud.controller;

import com.example.crud.dto.RegisterDto;
import com.example.crud.entity.User;
import com.example.crud.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/users")
    public List<User> findAll() {
        List<User> user = userService.findAll();
        return user;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/users/{id}")
    public User findUser(@PathVariable("id") Integer id) {
        User user = userService.findUser(id);
        return user;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/auth")
    public User register(@RequestBody RegisterDto registerDto) {
        User user = userService.register(registerDto);
        return user;
    }
}


