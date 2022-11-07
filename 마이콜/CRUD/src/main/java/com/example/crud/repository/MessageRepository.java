package com.example.crud.repository;

import com.example.crud.entity.Message;
import com.example.crud.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,Integer> {
    List<Message> findAllByReceiver(User user);
    List<Message> findAllBySender(User user);



}
