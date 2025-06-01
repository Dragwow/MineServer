package com.pet_project.backend_server.service;

import com.pet_project.backend_server.entity.user.User;
import com.pet_project.backend_server.entity.userProfile.UserProfile;

import java.util.Optional;

public interface UserProfileService extends CrudService<UserProfile> {

    Optional<UserProfile> findByUser(User user);
}
