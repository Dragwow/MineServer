package com.pet_project.backend_server.controller;

import com.pet_project.backend_server.dto.request.DataTableRequest;
import com.pet_project.backend_server.dto.response.DataTableResponse;
import com.pet_project.backend_server.dto.response.ResponseContainer;
import com.pet_project.backend_server.dto.response.projectResponse.ProjectResponse;
import com.pet_project.backend_server.facade.ProjectFacade;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Project page controller", description = "contains posts method")
@RestController
@RequestMapping("/api/projectPage")
@AllArgsConstructor
public class ProjectPageController {

    private final ProjectFacade projectFacade;

    @GetMapping("/projects")
    public ResponseEntity<ResponseContainer<DataTableResponse<ProjectResponse>>> findAllProjects(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "projectName") String sort,
            @RequestParam(defaultValue = "desc") String order) {
        DataTableRequest request = new DataTableRequest(page, size, sort, order);
        return ResponseEntity.ok(new ResponseContainer<>(projectFacade.findAll(request)));
    }
}
