package com.pet_project.backend_server.service;

import com.pet_project.backend_server.entity.messenger.Chat;

import java.util.List;

public interface ChatService extends CrudService<Chat>{
    List<Chat> findAllByMemberId(Long userId);

}
