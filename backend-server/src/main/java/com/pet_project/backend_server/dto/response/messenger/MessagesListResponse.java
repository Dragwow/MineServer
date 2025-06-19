package com.pet_project.backend_server.dto.response.messenger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessagesListResponse {

    private List<MessageDto> messages;
}
