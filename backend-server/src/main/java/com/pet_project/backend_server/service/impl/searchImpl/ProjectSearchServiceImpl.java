package com.pet_project.backend_server.service.impl.searchImpl;

import com.pet_project.backend_server.elastic.document.ProjectIndex;
import com.pet_project.backend_server.service.ProjectSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectSearchServiceImpl implements ProjectSearchService {
    @Override
    public List<ProjectIndex> searchProject(String projectName, String description, String username) {
        return List.of();
    }
}
