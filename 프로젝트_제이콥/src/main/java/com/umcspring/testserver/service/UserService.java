package com.umcspring.testserver.service;

import com.umcspring.testserver.dto.UserDto;
import com.umcspring.testserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public UserDto signup(UserDto user){
        return userRepository.signup(user);
    }

    public List<UserDto> getAllUsers(){
        return userRepository.getAllUsers();
    }

    public UserDto getUserById(String id){
        return userRepository.getUserById(id);
    }

    public void deleteUser(String id){
        userRepository.deleteUser(id);
    }
}
