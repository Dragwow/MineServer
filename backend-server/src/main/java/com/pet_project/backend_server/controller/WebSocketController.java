package com.pet_project.backend_server.controller;

import com.pet_project.backend_server.dto.request.messenger.MessageRequest;
import com.pet_project.backend_server.dto.response.dataResponse.DataSavedResponse;
import com.pet_project.backend_server.facade.MessageFacade;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class WebSocketController {

    private final MessageFacade messageFacade;

    @MessageMapping("/chat.send")
    @SendTo("/topic/public")
    public DataSavedResponse sendMessage(
            @Payload
            MessageRequest request,
            Principal principal
    ){
        return messageFacade.sendMessage(request, principal.getName());
    }
}
