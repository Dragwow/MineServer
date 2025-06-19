package com.pet_project.backend_server.service.impl;

import com.pet_project.backend_server.dto.request.DataTableRequest;
import com.pet_project.backend_server.dto.response.comment.CommentsResponse;
import com.pet_project.backend_server.entity.offer.OfferComments;
import com.pet_project.backend_server.entity.project.ProjectComment;
import com.pet_project.backend_server.repository.projectRepository.ProjectCommentsRepository;
import com.pet_project.backend_server.service.ProjectCommentsService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProjectCommentsServiceImpl implements ProjectCommentsService {

    private final ProjectCommentsRepository projectCommentsRepository;

    @Override
    public void create(ProjectComment entity) {
        projectCommentsRepository.save(entity);
    }

    @Override
    public void update(ProjectComment entity) {
        projectCommentsRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        projectCommentsRepository.deleteById(id);
    }

    @Override
    public ProjectComment findById(Long id) {
        return projectCommentsRepository.findById(id).orElseThrow();
    }

    @Override
    public Page<ProjectComment> findAll(DataTableRequest request) {
        return null;
    }

    @Override
    public List<CommentsResponse> findByProjectIdOrderByCreatedAtAsc(Long offerId) {
        List<ProjectComment> comments = projectCommentsRepository.findByProjectIdOrderByCreatedAtAsc(offerId);
        return comments.stream()
                .map(this::mapToCommentsResponse)
                .toList();
    }

    private CommentsResponse mapToCommentsResponse(ProjectComment projectComment){
        return CommentsResponse.builder()
                .id(projectComment.getId())
                .content(projectComment.getContent())
                .authorUsername(projectComment.getAuthorUsername().getUsername())
                .createdAt(projectComment.getCreatedAt())
                .build();

    }
}
