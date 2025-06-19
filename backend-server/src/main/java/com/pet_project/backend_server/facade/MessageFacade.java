package com.pet_project.backend_server.facade;

import com.pet_project.backend_server.dto.request.messenger.MessageRequest;
import com.pet_project.backend_server.dto.response.dataResponse.DataSavedResponse;
import com.pet_project.backend_server.dto.response.messenger.MessagesListResponse;

public interface MessageFacade {
    DataSavedResponse sendMessage(MessageRequest request, String senderUsername);
    MessagesListResponse getMessages(Long chatId, String requestUsername);
}
