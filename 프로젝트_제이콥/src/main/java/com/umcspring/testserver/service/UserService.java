package com.umcspring.testserver.service;

import com.umcspring.testserver.domain.User;
import com.umcspring.testserver.dto.UserDto;
import com.umcspring.testserver.repository.UserRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public Long join(User user) {
        validateDuplicatedUser(user);   //중복회원 검증
        userRepository.save(user);
        return user.getId();
    }

    //회원 가입시 예외처리 (이메일 중복 안됨)
    private void validateDuplicatedUser(User user) {
        List<User> findUser = userRepository.findByName(user.getName());
        if (!findUser.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다!");
        }
    }

    //회원 전체 조회
    public List<User> findAll() {
        return userRepository.findAll();
    }

    //회원 단건 조회
    public User findOne(Long userId) {
        return userRepository.findOne(userId);
    }
}
