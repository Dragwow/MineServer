package com.pet_project.backend_server.repository.user;

import com.pet_project.backend_server.entity.user.UserComments;
import com.pet_project.backend_server.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCommentsRepository extends BaseRepository<UserComments> {
    List<UserComments> findByUserProfileIdOrderByCreatedAtAsc(Long userId);
}
