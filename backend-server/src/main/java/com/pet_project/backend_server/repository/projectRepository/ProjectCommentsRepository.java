package com.pet_project.backend_server.repository.projectRepository;

import com.pet_project.backend_server.entity.offer.OfferComments;
import com.pet_project.backend_server.entity.project.ProjectComment;
import com.pet_project.backend_server.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectCommentsRepository extends BaseRepository<ProjectComment> {
    List<ProjectComment> findByProjectIdOrderByCreatedAtAsc(Long projectId);
}
