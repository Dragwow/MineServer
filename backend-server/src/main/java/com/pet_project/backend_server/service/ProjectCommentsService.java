package com.pet_project.backend_server.service;

import com.pet_project.backend_server.dto.response.comment.CommentsResponse;
import com.pet_project.backend_server.entity.project.ProjectComment;

import java.util.List;

public interface ProjectCommentsService extends CrudService<ProjectComment>{
    List<CommentsResponse> findByProjectIdOrderByCreatedAtAsc(Long projectId);

}
