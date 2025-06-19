package com.pet_project.backend_server.service.impl;

import com.pet_project.backend_server.dto.request.DataTableRequest;
import com.pet_project.backend_server.entity.messenger.Chat;
import com.pet_project.backend_server.repository.messenger.ChatRepository;
import com.pet_project.backend_server.service.ChatService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;

    @Override
    public void create(Chat entity) {
        chatRepository.save(entity);
    }

    @Override
    public void update(Chat entity) {
        chatRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        chatRepository.deleteById(id);
    }

    @Override
    public Chat findById(Long id) {
        return chatRepository.findById(id).orElseThrow();
    }

    @Override
    public Page<Chat> findAll(DataTableRequest request) {
        return null;
    }

    @Override
    public List<Chat> findAllByMemberId(Long userId) {
        return chatRepository.findAllByMemberId(userId);
    }
}
