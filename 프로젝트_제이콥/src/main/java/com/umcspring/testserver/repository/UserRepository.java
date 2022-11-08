package com.umcspring.testserver.repository;

import com.umcspring.testserver.dto.UserDto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    //원래는 db연결 필요
    static public ArrayList<UserDto> users;

    static{
        users = new ArrayList<>();
        users.add(new UserDto(1, "백계환", "id1", "id1@gmail.com", "pw1"));
        users.add(new UserDto(2, "박세진", "id2", "id2@gmail.com", "pw2"));
        users.add(new UserDto(3, "권용재", "id3", "id3@gmail.com", "pw3"));
    }
    //회원 가입
    public UserDto signup(UserDto user){
        users.add(user);
        return user;
    }

    //전체 회원 조회
    public List<UserDto> getAllUsers(){
        return users;
    }

    //한 명의 회원 조회 (id값으로)
    public UserDto getUserById(String id){
        return users.stream()
                .filter(userDto -> userDto.getId().equals(id))
                .findAny()
                .orElse(new UserDto(0, "", "", "", ""));
    }

    //유저 비밀번호 수정
    public void updateUserPw(String id, UserDto user){
        users.stream()
                .filter(userDto -> userDto.getId().equals(id))
                .findAny()
                .orElse(new UserDto(0,"","","",""))
                .setPassword(user.getPassword());
    }
    //회원 탈퇴
    public void deleteUser(String id){
        users.removeIf(userDto -> userDto.getId().equals(id));
    }
}
