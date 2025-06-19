package com.pet_project.backend_server.dto.response.messenger;

import com.pet_project.backend_server.entity.messenger.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class MessageDto {

    private Long id;

    private String content;

    private LocalDateTime createdAt;

    private String senderUsername;

    public MessageDto(Message message){
        this.id = message.getId();
        this.content = message.getContent();
        this.createdAt = message.getCreatedAt();
        this.senderUsername = message.getSender().getUsername();
    }
}
