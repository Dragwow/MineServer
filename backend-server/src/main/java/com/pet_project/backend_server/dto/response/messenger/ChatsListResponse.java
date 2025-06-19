package com.pet_project.backend_server.dto.response.messenger;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatsListResponse {

    private List<ChatDto> chats;
}
