package com.pet_project.backend_server.service;

import com.pet_project.backend_server.elastic.document.UserIndex;

import java.util.List;

public interface UserSearchService {

    List<UserIndex> findByUsername(String username);
    List<UserIndex> findByFirstName(String firstName);
    List<UserIndex> findByLastName(String lastName);

}
