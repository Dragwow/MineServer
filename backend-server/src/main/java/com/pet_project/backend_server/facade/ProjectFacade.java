package com.pet_project.backend_server.facade;

import com.pet_project.backend_server.dto.request.projectRequest.ProjectRequest;
import com.pet_project.backend_server.dto.response.projectResponse.ProjectResponse;

public interface ProjectFacade extends CrudFacade<ProjectRequest, ProjectResponse> {
}
