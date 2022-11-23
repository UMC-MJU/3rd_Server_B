package com.umcspring.testserver.service;

import com.umcspring.testserver.domain.User;
import com.umcspring.testserver.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceTest {

    @Autowired UserService userService;
    @Autowired UserRepository userRepository;

    @Test
    @Rollback(value = false)
    public void join() throws Exception {
        User user = new User();
        user.setName("백계환");
        Long savedId = userService.join(user);
        assertEquals(user, userRepository.findOne(savedId));
    }
}