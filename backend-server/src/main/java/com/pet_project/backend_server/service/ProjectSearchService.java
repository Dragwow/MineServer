package com.pet_project.backend_server.service;

import com.pet_project.backend_server.elastic.document.ProjectIndex;

import java.util.List;

public interface ProjectSearchService {
    List<ProjectIndex> searchByProjectName(String text);
    List<ProjectIndex> searchProjectByDescription(String text);
    List<ProjectIndex> searchProjectByUsername(String text);
}
