package com.pet_project.backend_server.service;

import com.pet_project.backend_server.elastic.document.ProjectIndex;

import java.util.List;

public interface ProjectSearchService {
    List<ProjectIndex> searchProject(String projectName, String description, String username);
}
