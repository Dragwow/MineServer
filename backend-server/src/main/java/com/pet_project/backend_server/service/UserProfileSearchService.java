package com.pet_project.backend_server.service;

import com.pet_project.backend_server.elastic.document.UserProfileIndex;

import java.util.List;

public interface UserProfileSearchService {
    List<UserProfileIndex> searchProfile(String username, String firstName, String lastName);
}
