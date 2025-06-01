package com.pet_project.backend_server.repository.userProfile;

import com.pet_project.backend_server.entity.user.User;
import com.pet_project.backend_server.entity.userProfile.UserProfile;
import com.pet_project.backend_server.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserProfileRepository extends BaseRepository<UserProfile> {

 Optional<UserProfile> findByUser(User user);

}
