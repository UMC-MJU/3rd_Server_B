package com.umcspring.testserver.repository;

import com.umcspring.testserver.domain.User;
import com.umcspring.testserver.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor   //final 변수만 생성자 생성
public class UserRepository {

    private final EntityManager em;

    public void save(User user) {
        em.persist(user);
    }

    //JPQL 이용
    //한 명의 회원 조회 (id값으로)
    public User findOne(Long id) {
        return em.find(User.class, id);
    }

    //전체 회원 조회
    public List<User> findAll() {
        return em.createQuery("select u from User u", User.class)
                .getResultList();
    }

    //유저 이름으로 찾기
    public List<User> findByName(String name) {
        return em.createQuery("select u from User u where u.name = :name", User.class)
                .setParameter("name", name)
                .getResultList();
    }

    //유저 이메일로 찾기 (중복 검사시 이용)
    public User findByEmail(String email) {
        return em.find(User.class, email);
    }
//    //유저 비밀번호 수정
//    public void updateUserPw(String id, UserDto user) {
//
//    }
//
//    //회원 탈퇴
//    public void deleteUser(String id) {
//
//    }
}
