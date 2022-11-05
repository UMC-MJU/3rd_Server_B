package com.example.crud.controller;

import com.example.crud.dto.MessageDto;
import com.example.crud.entity.Message;
import com.example.crud.entity.User;
import com.example.crud.repository.UserRepository;
import com.example.crud.service.MessageService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class MessageController {
    private final MessageService messageService;
    private final UserRepository userRepository;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/messages")
    public MessageDto sendMessage(@RequestBody MessageDto messageDto) {

        User user = userRepository.findById(1).orElseThrow(() -> {
            return new IllegalArgumentException("유저를 찾을 수 없습니다.");
        });
        messageDto.setSenderName(user.getNickname());


        return messageService.writeMessage(messageDto);
    }



    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/messages/receive")
    public List<MessageDto> getReceivedMessage() {

        User user = userRepository.findById(1).orElseThrow(() -> {
            return new IllegalArgumentException("유저를 찾을 수 없습니다.");
        });

        return messageService.getMessages(user);
    }


    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/messages/delete/{id}")
    public void deleteReceivedMessage(@PathVariable("id") Integer id) {

        User user = userRepository.findById(1).orElseThrow(() -> {
            return new IllegalArgumentException("유저를 찾을 수 없습니다.");
        });

        messageService.deleteMessage(id, user);
    }



}
