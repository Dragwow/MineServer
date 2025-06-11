package com.pet_project.backend_server.repository.user;

import com.pet_project.backend_server.entity.user.User;
import com.pet_project.backend_server.repository.BaseRepository;
import com.pet_project.backend_server.repository.data.LanguageSearchDto;
import com.pet_project.backend_server.repository.data.UserSearchDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User> {
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);

    @Query(value = "select distinct new com.pet_project.backend_server.repository.data.UserSearchDto(pv, pv.itLanguages, pv.languages) from User pv")
    List<UserSearchDto> findAllUserSearchDto();
}
