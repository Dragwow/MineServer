package com.pet_project.backend_server.facade;

import com.pet_project.backend_server.dto.request.messenger.ChatRequest;
import com.pet_project.backend_server.dto.response.dataResponse.DataSavedResponse;
import com.pet_project.backend_server.dto.response.messenger.ChatsListResponse;

public interface ChatFacade {
    ChatsListResponse getUserChats(String username);
    DataSavedResponse createUserChat(ChatRequest request);
}
