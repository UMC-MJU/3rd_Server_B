package com.umc.board_practice.provider;

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
@Transactional(readOnly = true)
public class UserProvider {

    private final UserRepository userRepository;

    public UserDto findUserByPassword(String password) {
        User findUser = userRepository.findByPassword(password);
        return UserDto.builder()
                .name(findUser.getName())
                .password(findUser.getPassword())
                .build();
    }

    public List<UserDto> findAllUser() {
        List<User> findUsers = userRepository.findAll();
        return findUsers.stream()
                .map(User::toUserDto)
                .collect(Collectors.toList());
    }
}
