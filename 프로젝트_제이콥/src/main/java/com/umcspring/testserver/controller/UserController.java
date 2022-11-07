package com.umcspring.testserver.controller;

import com.umcspring.testserver.dto.UserDto;
import com.umcspring.testserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    //회원가입
    @PostMapping("")
    public UserDto signup(@RequestBody UserDto user){
        return userService.signup(user);
    }

    //전체 회원 조회
    @GetMapping("")
    public List<UserDto> getAllUsers(){
        return userService.getAllUsers();
    }

    //회원 한 명 조회
    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable String id){
        return userService.getUserById(id);
    }

    //회원 탈퇴
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id){
        userService.deleteUser(id);
    }
}
