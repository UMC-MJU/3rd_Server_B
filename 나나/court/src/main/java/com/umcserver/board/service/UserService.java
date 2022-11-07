package com.umcserver.board.service;

import com.umcserver.board.dto.UserDTO;
import com.umcserver.board.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public UserDTO insertUser(UserDTO user) {
        return userRepository.insertUser(user);
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public UserDTO getUserByUserID(String id) {
        return userRepository.getUserByID(id);
    }

    public void updateUserPw(String id, UserDTO user) {
        userRepository.updateUserPw(id, user);
    }

    public void deleteUser(String id) {
        userRepository.deleteUser(id);
    }

}
