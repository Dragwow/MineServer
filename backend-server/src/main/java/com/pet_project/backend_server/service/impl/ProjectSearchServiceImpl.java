package com.pet_project.backend_server.service.impl;

import com.pet_project.backend_server.elastic.document.ProjectIndex;
import com.pet_project.backend_server.elastic.repository.ProjectIndexRepository;
import com.pet_project.backend_server.service.ProjectSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectSearchServiceImpl implements ProjectSearchService {

    private final ProjectIndexRepository projectIndexRepository;

    @Override
    public List<ProjectIndex> searchByProjectName(String text) {
        return projectIndexRepository.searchByProjectName(text);
    }

    @Override
    public List<ProjectIndex> searchProjectByDescription(String text) {
        return projectIndexRepository.searchByDescription(text);
    }

    @Override
    public List<ProjectIndex> searchProjectByUsername(String text) {
        return projectIndexRepository.searchByCreatedBy(text);
    }
}
