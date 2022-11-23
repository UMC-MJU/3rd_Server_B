package com.umcserver.board.controller;

import com.umcserver.board.dto.UserDTO;
import com.umcserver.board.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    @GetMapping(value = "api/test")
    public String test() {
        return "hello~";
    }

    @Autowired
    UserService userService;

    @PostMapping("")
    public UserDTO insertUser(@RequestBody UserDTO user) {
        return userService.insertUser(user);
    }

    @GetMapping("")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{ID}")
    public UserDTO getUserByUserID(@PathVariable String ID) {
        return userService.getUserByUserID(ID);
    }

    @PutMapping("/{ID}")
    public void updateUserPw(@PathVariable String ID, @RequestBody UserDTO user) {
        userService.updateUserPw(ID, user);
    }

    @DeleteMapping("/{ID}")
    public void deleteUser(@PathVariable String ID) {
        userService.deleteUser(ID);
    }
}
