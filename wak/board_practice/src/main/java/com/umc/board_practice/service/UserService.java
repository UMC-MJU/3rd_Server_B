package com.umc.board_practice.service;

import com.umc.board_practice.domain.Post;
import com.umc.board_practice.domain.User;
import com.umc.board_practice.dto.UserDto;
import com.umc.board_practice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserDto signUp(UserDto userDto) {
        User newUser = User.createUser(userDto);
        User savedUser = userRepository.save(newUser);
        return UserDto.builder()
                .name(savedUser.getName())
                .password(savedUser.getPassword())
                .build();
    }

    public UserDto findUserByPassword(String password) {
        User findUser = userRepository.findByPassword(password);
        return UserDto.builder()
                .name(findUser.getName())
                .password(findUser.getPassword())
                .build();
    }

    public List<UserDto> findAllUser() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(User::toUserDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteUserByPassword(String password) {
        User findUser = userRepository.findByPassword(password);
        userRepository.delete(findUser);
    }

    @Transactional
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
