package com.pet_project.backend_server.repository.messenger;

import com.pet_project.backend_server.entity.messenger.Message;
import com.pet_project.backend_server.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends BaseRepository<Message> {
    List<Message> findByChatIdOrderByCreatedAtAsc(Long chatId);
}
