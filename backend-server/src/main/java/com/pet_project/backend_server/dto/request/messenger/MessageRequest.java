package com.pet_project.backend_server.dto.request.messenger;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(name = "Message request")
public class MessageRequest {

    @NotNull
    @Schema(description = "Chat id")
    private Long chatId;

    @NotEmpty
    @Schema(description = "Content")
    private String content;

}
