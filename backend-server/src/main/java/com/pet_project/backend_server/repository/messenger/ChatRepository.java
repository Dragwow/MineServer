package com.pet_project.backend_server.repository.messenger;

import com.pet_project.backend_server.entity.messenger.Chat;
import com.pet_project.backend_server.repository.BaseRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends BaseRepository<Chat> {

    @EntityGraph(attributePaths = {"members"})
    @Query("SELECT c FROM Chat c JOIN c.members m WHERE m.id = :userId")
    List<Chat> findAllByMemberId(Long userId);
}
