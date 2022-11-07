package com.example.crud.dto;


import com.example.crud.entity.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {
    private String title;
    private String comment;
    private String senderName;
    private String receiverName;

    public static MessageDto toDto(Message message) {
        return new MessageDto(
                message.getTitle(),
                message.getComment(),
                message.getSender().getNickname(),
                message.getReceiver().getNickname()
        );
    }
}
