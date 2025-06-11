package com.pet_project.backend_server.repository.data;

import com.pet_project.backend_server.entity.user.User;
import com.pet_project.backend_server.entity.userProfile.UserProfile;

public record UserProfileSearchDto (UserProfile userProfile, User user) {
}
