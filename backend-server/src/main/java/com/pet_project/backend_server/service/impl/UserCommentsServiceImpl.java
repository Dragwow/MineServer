package com.pet_project.backend_server.service.impl;

import com.pet_project.backend_server.dto.request.DataTableRequest;
import com.pet_project.backend_server.dto.response.comment.CommentsResponse;
import com.pet_project.backend_server.entity.project.ProjectComment;
import com.pet_project.backend_server.entity.user.UserComments;
import com.pet_project.backend_server.repository.user.UserCommentsRepository;
import com.pet_project.backend_server.service.UserCommentsService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserCommentsServiceImpl implements UserCommentsService {

    private final UserCommentsRepository userCommentsRepository;

    @Override
    public void create(UserComments entity) {
        userCommentsRepository.save(entity);
    }

    @Override
    public void update(UserComments entity) {
        userCommentsRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        userCommentsRepository.deleteById(id);
    }

    @Override
    public UserComments findById(Long id) {
        return userCommentsRepository.findById(id).orElseThrow();
    }

    @Override
    public Page<UserComments> findAll(DataTableRequest request) {
        return null;
    }

    @Override
    public List<CommentsResponse> findByUserProfileIdOrderByCreatedAtAsc(Long userProfileId) {
        List<UserComments> comments = userCommentsRepository.findByUserProfileIdOrderByCreatedAtAsc(userProfileId);
        return comments.stream()
                .map(this::mapToCommentsResponse)
                .toList();
    }

    private CommentsResponse mapToCommentsResponse(UserComments userComments){
        return CommentsResponse.builder()
                .id(userComments.getId())
                .content(userComments.getContent())
                .authorUsername(userComments.getAuthorUsername().getUsername())
                .createdAt(userComments.getCreatedAt())
                .build();

    }
}
