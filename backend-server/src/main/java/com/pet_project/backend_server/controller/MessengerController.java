package com.pet_project.backend_server.controller;

import com.pet_project.backend_server.dto.request.messenger.ChatRequest;
import com.pet_project.backend_server.dto.response.dataResponse.DataSavedResponse;
import com.pet_project.backend_server.dto.response.ResponseContainer;
import com.pet_project.backend_server.dto.response.messenger.ChatsListResponse;
import com.pet_project.backend_server.dto.response.messenger.MessagesListResponse;
import com.pet_project.backend_server.facade.ChatFacade;
import com.pet_project.backend_server.facade.MessageFacade;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Tag(name = "Messenger controller", description = "contains messenger")
@RestController
@RequestMapping("/api/chat")
@AllArgsConstructor
public class MessengerController {

    private final ChatFacade chatFacade;
    private final MessageFacade messageFacade;

    @GetMapping
    public ResponseEntity<ResponseContainer<ChatsListResponse>> chatList(
            Principal principal
            ){
        return ResponseEntity.ok(new ResponseContainer<>(chatFacade.getUserChats(principal.getName())));
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseContainer<DataSavedResponse>> createChat(
            @RequestBody
            ChatRequest request){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new  ResponseContainer<>(chatFacade.createUserChat(request)));
    }

    @GetMapping("/{chatId}/messages")
    public ResponseEntity<ResponseContainer<MessagesListResponse>> messageList(
            @RequestParam
            Long chatId,
            Principal principal){
        return  ResponseEntity.ok(new ResponseContainer<>(messageFacade.getMessages(chatId, principal.getName())));
    }
}
