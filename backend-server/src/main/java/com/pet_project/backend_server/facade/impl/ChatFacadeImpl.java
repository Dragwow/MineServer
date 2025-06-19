package com.pet_project.backend_server.facade.impl;

import com.pet_project.backend_server.dto.request.messenger.ChatRequest;
import com.pet_project.backend_server.dto.response.dataResponse.DataSavedResponse;
import com.pet_project.backend_server.dto.response.messenger.ChatDto;
import com.pet_project.backend_server.dto.response.messenger.ChatsListResponse;
import com.pet_project.backend_server.entity.messenger.Chat;
import com.pet_project.backend_server.entity.user.User;
import com.pet_project.backend_server.facade.ChatFacade;
import com.pet_project.backend_server.service.ChatService;
import com.pet_project.backend_server.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;;

@Service
@AllArgsConstructor
public class ChatFacadeImpl implements ChatFacade {

    private final ChatService chatService;
    private final UserService userService;

    @Override
    public ChatsListResponse getUserChats(String username) {
        User user = userService.findByUsername(username);

        List<Chat> chat = chatService.findAllByMemberId(user.getId());

        List<ChatDto> chatDtoList = chat.stream()
                .map(this::getListOfChats)
                .toList();

        return ChatsListResponse
                .builder()
                .chats(chatDtoList)
                .build();

    }

    @Override
    public DataSavedResponse createUserChat(ChatRequest request) {
        User user1 = userService.findByUsername(request.getUsername1());
        User user2 = userService.findByUsername(request.getUsername2());

        Chat chat = new Chat();
        chat.getMembers().addAll(List.of(user1,user2));
        chatService.create(chat);
        return new DataSavedResponse();
    }

    private ChatDto getListOfChats(Chat chat){
        return new ChatDto(
                chat.getId(),
                chat.getMembers().stream().map(User::getUsername)
                        .toList()
        );
    }
}
