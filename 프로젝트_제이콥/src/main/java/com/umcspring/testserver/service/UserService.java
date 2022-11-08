package com.umcspring.testserver.service;

import com.umcspring.testserver.dto.UserDto;
import com.umcspring.testserver.repository.UserRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserDto signup(UserDto user){
        return userRepository.signup(user);
    }

    public List<UserDto> getAllUsers(){
        return userRepository.getAllUsers();
    }

    public UserDto getUserById(String id){
        return userRepository.getUserById(id);
    }

    public void updateUserPw(String id, UserDto user) {
        userRepository.updateUserPw(id, user);
    }
    public void deleteUser(String id){
        userRepository.deleteUser(id);
    }
}
