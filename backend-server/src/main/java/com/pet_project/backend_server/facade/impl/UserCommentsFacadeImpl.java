package com.pet_project.backend_server.facade.impl;

import com.pet_project.backend_server.dto.request.comment.CommentsRequest;
import com.pet_project.backend_server.dto.response.comment.CommentsResponse;
import com.pet_project.backend_server.entity.user.User;
import com.pet_project.backend_server.entity.user.UserComments;
import com.pet_project.backend_server.facade.UserCommentsFacade;
import com.pet_project.backend_server.service.UserCommentsService;
import com.pet_project.backend_server.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class UserCommentsFacadeImpl implements UserCommentsFacade {

    private final UserCommentsService userCommentsService;
    private final UserService userService;


    @Override
    public CommentsResponse addComment(CommentsRequest request, String username) {
        User user = userService.findByUsername(username);

        UserComments comment = new UserComments();
        comment.setUserProfile(user);
        comment.setAuthorUsername(user);
        comment.setContent(request.getContent());
        comment.setCreatedAt(LocalDateTime.now());

        userCommentsService.create(comment);

        return new CommentsResponse(comment);
    }

    @Override
    public List<CommentsResponse> getComments(Long userProfileId) {
        return userCommentsService.findByUserProfileIdOrderByCreatedAtAsc(userProfileId);
    }
}
