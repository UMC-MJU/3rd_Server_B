package com.example.crud.service;

import com.example.crud.dto.MessageDto;
import com.example.crud.entity.Message;
import com.example.crud.entity.User;
import com.example.crud.repository.MessageRepository;
import com.example.crud.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    @Transactional
    public MessageDto writeMessage(MessageDto messageDto) {
        User receiver = userRepository.findByNickname(messageDto.getReceiverName());
        User sender = userRepository.findByNickname(messageDto.getSenderName());

        Message m = new Message();
        m.setReceiver(receiver);
        m.setSender(sender);
        m.setTitle(messageDto.getTitle());
        m.setComment(messageDto.getComment());
        messageRepository.save(m);

        return MessageDto.toDto(m);
    }

    @Transactional
    public List<MessageDto> getMessages(User user) {
        List<Message> messages = messageRepository.findAllByReceiver(user);
        List<MessageDto> messageDtos = new ArrayList<>();

        for(Message message : messages) {
            messageDtos.add(MessageDto.toDto(message));
        }
        return messageDtos;
    }

    @Transactional
    public void deleteMessage(int id, User user) {
        Message message = messageRepository.findById(id).orElseThrow(()->{
            throw new IllegalArgumentException("쪽지를 찾을 수 없습니다.");
        });
        if(user == message.getReceiver()) {
            messageRepository.delete(message);
        } else {
            throw new IllegalArgumentException("sender는 메세지 삭제 x");
        }
    }


}
