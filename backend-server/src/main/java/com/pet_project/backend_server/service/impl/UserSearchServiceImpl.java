package com.pet_project.backend_server.service.impl;

import com.pet_project.backend_server.elastic.document.UserIndex;
import com.pet_project.backend_server.elastic.repository.UserIndexRepository;
import com.pet_project.backend_server.service.UserSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserSearchServiceImpl implements UserSearchService {

    private final UserIndexRepository userIndexRepository;

    @Override
    public List<UserIndex> findByUsername(String username) {
        return userIndexRepository.searchProfileByUsername(username);
    }

    @Override
    public List<UserIndex> findByFirstName(String firstName) {
        return userIndexRepository.searchProfileByFirstName(firstName);
    }

    @Override
    public List<UserIndex> findByLastName(String lastName) {
        return userIndexRepository.searchProfileByLastName(lastName);
    }
}
