package com.pet_project.backend_server.dto.request.comment;

import com.pet_project.backend_server.dto.request.ApiRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Schema(name = "Comments request")
public class CommentsRequest extends ApiRequest {

    private Long postId;

    @Schema(description = "Comment content")
    private String content;

}
