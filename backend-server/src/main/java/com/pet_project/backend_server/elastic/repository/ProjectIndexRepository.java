package com.pet_project.backend_server.elastic.repository;

import com.pet_project.backend_server.elastic.document.ProjectIndex;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectIndexRepository extends ElasticsearchRepository<ProjectIndex, Long> {

    List<ProjectIndex> searchByProjectName(String projectName);
    List<ProjectIndex> searchByDescription(String description);
    List<ProjectIndex> searchByCreatedBy(String createdBy);

}
