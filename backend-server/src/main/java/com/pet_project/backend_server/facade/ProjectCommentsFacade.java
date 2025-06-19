package com.pet_project.backend_server.facade;

import com.pet_project.backend_server.dto.request.comment.CommentsRequest;
import com.pet_project.backend_server.dto.response.comment.CommentsResponse;

import java.util.List;

public interface ProjectCommentsFacade {
    CommentsResponse addComment(CommentsRequest request, String username);
    List<CommentsResponse> getComments(Long offerId);
}
