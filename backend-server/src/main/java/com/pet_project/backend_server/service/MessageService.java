package com.pet_project.backend_server.service;

import com.pet_project.backend_server.dto.response.messenger.MessageDto;
import com.pet_project.backend_server.entity.messenger.Message;

import java.util.List;

public interface MessageService extends CrudService<Message>{
    List<MessageDto> findByChatIdOrderByCreatedAtAsc(Long chatId);
}
