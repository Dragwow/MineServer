package com.pet_project.backend_server.repository.user;

import com.pet_project.backend_server.entity.user.User;
import com.pet_project.backend_server.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User> {
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);


}
