package com.pet_project.backend_server.service;

import com.pet_project.backend_server.dto.response.comment.CommentsResponse;
import com.pet_project.backend_server.entity.user.UserComments;

import java.util.List;

public interface UserCommentsService extends CrudService<UserComments>{
    List<CommentsResponse> findByUserProfileIdOrderByCreatedAtAsc(Long offerId);
}
