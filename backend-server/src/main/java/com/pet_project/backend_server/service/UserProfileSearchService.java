package com.pet_project.backend_server.service;

import java.util.List;

public interface UserProfileSearchService {
    List<UserProfileIndex> searchProfile(String username, String firstName, String lastName);
}
