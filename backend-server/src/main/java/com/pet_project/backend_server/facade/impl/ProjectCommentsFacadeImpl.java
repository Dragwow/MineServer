package com.pet_project.backend_server.facade.impl;

import com.pet_project.backend_server.dto.request.comment.CommentsRequest;
import com.pet_project.backend_server.dto.response.comment.CommentsResponse;
import com.pet_project.backend_server.entity.project.Project;
import com.pet_project.backend_server.entity.project.ProjectComment;
import com.pet_project.backend_server.entity.user.User;
import com.pet_project.backend_server.facade.ProjectCommentsFacade;
import com.pet_project.backend_server.service.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ProjectCommentsFacadeImpl implements ProjectCommentsFacade {

    private final ProjectCommentsService projectCommentsService;
    private final UserService userService;
    private final ProjectService projectService;


    @Override
    public CommentsResponse addComment(CommentsRequest request, String username) {
        Project project = projectService.findById(request.getPostId());
        User user = userService.findByUsername(username);

        ProjectComment comment = new ProjectComment();
        comment.setProject(project);
        comment.setAuthorUsername(user);
        comment.setContent(request.getContent());
        comment.setCreatedAt(LocalDateTime.now());

        projectCommentsService.create(comment);

        return new CommentsResponse(comment);
    }

    @Override
    public List<CommentsResponse> getComments(Long projectId) {
        return projectCommentsService.findByProjectIdOrderByCreatedAtAsc(projectId);
    }
}
