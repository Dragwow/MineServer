package com.pet_project.backend_server.facade.impl;

import com.pet_project.backend_server.dto.request.messenger.MessageRequest;
import com.pet_project.backend_server.dto.response.dataResponse.DataSavedResponse;
import com.pet_project.backend_server.dto.response.messenger.MessageDto;
import com.pet_project.backend_server.dto.response.messenger.MessagesListResponse;
import com.pet_project.backend_server.entity.messenger.Chat;
import com.pet_project.backend_server.entity.messenger.Message;
import com.pet_project.backend_server.entity.user.User;
import com.pet_project.backend_server.facade.MessageFacade;
import com.pet_project.backend_server.service.ChatService;
import com.pet_project.backend_server.service.MessageService;
import com.pet_project.backend_server.service.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class MessageFacadeImpl implements MessageFacade {

    private final UserService userService;
    private final ChatService chatService;
    private final MessageService messageService;


    @Transactional
    @Override
    public DataSavedResponse sendMessage(MessageRequest request, String senderUsername) {
        Chat chat = chatService.findById(request.getChatId());
        User sender = userService.findByUsername(senderUsername);

        if (!chat.getMembers().contains(sender)){
            throw new RuntimeException("Access denied to chat");
        }

        Message message = new Message();
        message.setChat(chat);
        message.setSender(sender);
        message.setContent(request.getContent());
        message.setCreatedAt(LocalDateTime.now());
        messageService.create(message);
        return new DataSavedResponse();

    }

    @Transactional
    @Override
    public MessagesListResponse getMessages(Long chatId, String requestUsername) {
        Chat chat = chatService.findById(chatId);
        User user = userService.findByUsername(requestUsername);

        if (!chat.getMembers().contains(user)){
            throw new RuntimeException("Access denied to chat");
        }

        List<MessageDto> messages = messageService.findByChatIdOrderByCreatedAtAsc(chatId);

        return new  MessagesListResponse(messages);
    }
}
