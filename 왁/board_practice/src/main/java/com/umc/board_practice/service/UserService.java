package com.umc.board_practice.service;

import com.umc.board_practice.domain.User;
import com.umc.board_practice.dto.UserDto;
import com.umc.board_practice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserDto signUp(UserDto userDto) {
        User newUser = User.createUser(userDto);
        User savedUser = userRepository.save(newUser);
        return UserDto.builder()
                .name(savedUser.getName())
                .password(savedUser.getPassword())
                .build();
    }


    public void deleteUserByPassword(String password) {
        User findUser = userRepository.findByPassword(password);
        userRepository.delete(findUser);
    }

    public UserDto updateUserByPassword(String password, UserDto userDto) {
        User findUser = userRepository.findByPassword(password);
        findUser.setPassword(userDto.getPassword());
        findUser.setName(userDto.getName());
        return UserDto.builder()
                .name(findUser.getName())
                .password(findUser.getPassword())
                .build();
    }

}
