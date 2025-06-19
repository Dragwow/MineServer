package com.pet_project.backend_server.dto.response.comment;

import com.pet_project.backend_server.dto.response.ApiResponse;
import com.pet_project.backend_server.entity.offer.OfferComments;
import com.pet_project.backend_server.entity.project.ProjectComment;
import com.pet_project.backend_server.entity.user.UserComments;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentsResponse extends ApiResponse<Long> {

    private Long id;

    private String content;

    private String authorUsername;

    private LocalDateTime createdAt;

    public CommentsResponse(OfferComments comments) {
        this.id = comments.getId();
        this.content = comments.getContent();
        this.authorUsername = comments.getAuthorUsername().getUsername();
        this.createdAt = comments.getCreatedAt();
    }

    public CommentsResponse(ProjectComment comments) {
        this.id = comments.getId();
        this.content = comments.getContent();
        this.authorUsername = comments.getAuthorUsername().getUsername();
        this.createdAt = comments.getCreatedAt();
    }

    public CommentsResponse(UserComments comments) {
        this.id = comments.getId();
        this.content = comments.getContent();
        this.authorUsername = comments.getAuthorUsername().getUsername();
        this.createdAt = comments.getCreatedAt();
    }


}
