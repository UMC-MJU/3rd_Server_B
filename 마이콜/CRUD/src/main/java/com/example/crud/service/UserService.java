package com.example.crud.service;

import com.example.crud.dto.RegisterDto;
import com.example.crud.entity.User;
import com.example.crud.exception.UserNotFoundException;
import com.example.crud.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User register(RegisterDto registerDto) {
        User user = new User();
        user.setNickname(registerDto.getNickname());
        user.setPassword(registerDto.getPassword());
        user.setUsername(registerDto.getUsername());
        return userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findUser(int id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }
}


