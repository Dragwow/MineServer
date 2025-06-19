package com.pet_project.backend_server.service.impl;

import com.pet_project.backend_server.dto.request.DataTableRequest;
import com.pet_project.backend_server.dto.response.messenger.MessageDto;
import com.pet_project.backend_server.entity.messenger.Message;
import com.pet_project.backend_server.repository.messenger.MessageRepository;
import com.pet_project.backend_server.service.MessageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    @Override
    public void create(Message entity) {
        messageRepository.save(entity);
    }

    @Override
    public void update(Message entity) {
        messageRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        messageRepository.deleteById(id);
    }

    @Override
    public Message findById(Long id) {
        return messageRepository.findById(id).orElseThrow();
    }


    @Override
    public Page<Message> findAll(DataTableRequest request) {
        return null;
    }

    @Override
    public List<MessageDto> findByChatIdOrderByCreatedAtAsc(Long chatId) {
        List<Message> messages = messageRepository.findByChatIdOrderByCreatedAtAsc(chatId);
        return messages.stream()
                .map(MessageDto::new)
                .toList();
    }
}
