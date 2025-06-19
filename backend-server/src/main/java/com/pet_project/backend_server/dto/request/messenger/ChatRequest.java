package com.pet_project.backend_server.dto.request.messenger;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(name = "Creation chat request")
public class ChatRequest {

    @NotNull
    @Schema(description = "Chat id")
    private Long chatId;

    @NotNull
    @Schema(description = "First Username")
    private String username1;

    @NotNull
    @Schema(description = "Second username")
    private String username2;
}
