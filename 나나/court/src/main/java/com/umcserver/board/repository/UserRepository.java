package com.umcserver.board.repository;

import com.umcserver.board.dto.UserDTO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    // 원래 DB 연동하는 코드를 넣어야 하지만 임시로 더미데이터로 테스트
    static public ArrayList<UserDTO> users;

    static {
        users = new ArrayList<>();
        users.add(new UserDTO(1, "a1", "b1", "헬로키티", "a1@gmail.com"));
        users.add(new UserDTO(2, "a2", "b2", "폼폼푸린", "a2@gmail.com"));
        users.add(new UserDTO(3, "a3", "b3", "쿠로미", "a3@gmail.com"));
    }
    // 회원가입
    public UserDTO insertUser(UserDTO user) {
        users.add(user);
        return user;
    }

    // 전체 유저 조회
    public List<UserDTO> getAllUsers() {
        return users;
    }

    // 한 명의 유저만 조회
    public UserDTO getUserByID(String id) {
        return users.stream()
                .filter(userDTO -> userDTO.getID().equals(id))
                .findAny()
                .orElse(new UserDTO(0, "", "", "", ""));
    }

    // 유저 비밀번호 수정
    public void updateUserPw(String id, UserDTO user) {
        users.stream()
                .filter(userDTO -> userDTO.getID().equals(id))
                .findAny()
                .orElse(new UserDTO(0, "", "", "", ""))
                .setPassword(user.getPassword());
    }

    // 회원 삭제
    public void deleteUser(String id) {
        users.removeIf(userDTO -> userDTO.getID().equals(id));
    }

}
