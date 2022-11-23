package com.umcspring.testserver.service;

import com.umcspring.testserver.domain.Post;
import com.umcspring.testserver.domain.User;
import com.umcspring.testserver.repository.PostRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PostServiceTest {

    @Autowired PostService postService;
    @Autowired PostRepository postRepository;

    @Test
    @Rollback(value = false)
    public void posting() throws Exception{
        Post post = new Post();
        User user = new User();
        user.setName("gyehwan");
        Long saveId = postService.posting(post, user.getId());
        assertEquals(post, postRepository.findOne(saveId));
    }
}