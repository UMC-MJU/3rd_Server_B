package com.umcspring.testserver.controller;

import com.umcspring.testserver.domain.User;
import com.umcspring.testserver.dto.UserDto;
import com.umcspring.testserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    //회원가입
    @PostMapping("")
    public Long signup(@RequestBody User user){
        return userService.join(user);
    }

    //전체 회원 조회
    @GetMapping("")
    public List<User> findAll(){
        return userService.findAll();
    }

    //회원 한 명 조회
    @GetMapping("/{id}")
    public User findUserById(@PathVariable Long id){
        return userService.findOne(id);
    }

    //회원 정보 수정
//    @PutMapping("/{id}")
//    public void updateUserPw(@PathVariable String id, @RequestBody User user){
//        userService.updateUserPw(id, user);
//    }

    //회원 탈퇴
//    @DeleteMapping("/{id}")
//    public void deleteUser(@PathVariable String id){
//        userService.deleteUser(id);
//    }
}
