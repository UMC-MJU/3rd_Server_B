package com.umc.board_practice.service;

import com.umc.board_practice.domain.User;
import com.umc.board_practice.dto.UserDto;
import com.umc.board_practice.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserRepository userRepository;



    @Test
    void signUp() {
        UserDto myDto = UserDto.builder()
                .name("wee")
                .password("004637")
                .build();
        User newUser = User.createUser(myDto);
        User savedUser = userRepository.save(newUser);
        UserDto resultDto =  UserDto.builder()
                .name(savedUser.getName())
                .password(savedUser.getPassword())
                .build();
        Assertions.assertThat(myDto).isEqualTo(resultDto);
        //Assertions.assertThat(resultDto).isNull();
    }

    @Test
    void findUserByPassword() {
    }

    @Test
    void findAllUser() {
    }

    @Test
    void deleteUserByPassword() {
    }

    @Test
    void updateUserByPassword() {
    }
}