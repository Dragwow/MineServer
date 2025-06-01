package com.pet_project.backend_server.dto.request.projectRequest;

import com.pet_project.backend_server.dto.request.ApiRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Schema(description = "Project request")
public class ProjectRequest extends ApiRequest{

    private List<ProjectRequestItem> projects;
}
