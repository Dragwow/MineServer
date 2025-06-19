package com.pet_project.backend_server.facade.impl;

import com.pet_project.backend_server.dto.request.DataTableRequest;
import com.pet_project.backend_server.dto.request.projectRequest.ProjectRequest;
import com.pet_project.backend_server.dto.request.projectRequest.ProjectRequestItem;
import com.pet_project.backend_server.dto.response.DataTableResponse;
import com.pet_project.backend_server.dto.response.projectResponse.ProjectResponse;
import com.pet_project.backend_server.entity.project.Project;
import com.pet_project.backend_server.exception.EntityNotFoundException;
import com.pet_project.backend_server.facade.ProjectFacade;
import com.pet_project.backend_server.service.ProjectService;
import com.pet_project.backend_server.service.UserService;
import com.pet_project.backend_server.util.ExceptionUtil;
import lombok.AllArgsConstructor;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ProjectFacadeImpl implements ProjectFacade {

    private final UserService userService;
    private final ProjectService projectService;

    @Override
    public void create(ProjectRequest request){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        var user = userService.findByUsername(username);

        List<ProjectRequestItem> items = request.getProjects();

        if (items.isEmpty()){
            throw new IllegalArgumentException("Project must not be empty");
        }

        List<Project> projects = items.stream()
                .map(dto -> {
                    Project project = new Project();
                    project.setUser(user);
                    project.setNameProject(dto.getNameProject());
                    project.setDescription(dto.getDescriptionProject());
                    project.setCreatedBy(username);
                    project.setLinkGit(dto.getLinkGit());
                    project.setCreatedAt(LocalDateTime.now());
                    return project;
                }).toList();

        projects.forEach(projectService::create);
    }

    @Override
    public void update(ProjectRequest request, Long id) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        var user = userService.findByUsername(username);

        List<ProjectRequestItem> items = request.getProjects();

        if (items.isEmpty()){
            throw new IllegalArgumentException("Project must not be empty");
        }

        for (ProjectRequestItem dto : items){
            if (dto.getId() == null){
                throw new IllegalArgumentException("Project ID must not be update");
            }

            Project project = projectService.findById(dto.getId());


            if (!project.getUser().getId().equals(user.getId())) {
                throw new AccessDeniedException("You are not allowed to update to this project");
            }
            project.setNameProject(dto.getNameProject());
            project.setDescription(dto.getDescriptionProject());
            project.setLinkGit(dto.getLinkGit());
            project.setUpdatedAt(LocalDateTime.now());
        }
    }

    @Override
    public void delete(Long id) {
        projectService.delete(id);
    }

    @Override
    public ProjectResponse findById(Long id) {
       return null;
    }

    @Override
    @Cacheable(value = "projectsCache",
            key = "#request.page + '-' + #request.size + '-' + #request.sort + '-' + #request.order")
    public DataTableResponse<ProjectResponse> findAll(DataTableRequest request) {
        Page<Project> page = projectService.findAll(request);
        DataTableResponse<ProjectResponse> dataTableResponse = new DataTableResponse<>();
        dataTableResponse.setSort(request.getSort());
        dataTableResponse.setOrder(request.getOrder());
        List<ProjectResponse> projectResponseList = page.getContent()
                .stream()
                .map(ProjectResponse::new)
                .toList();
        dataTableResponse.setItems(projectResponseList);
        return dataTableResponse;
    }
}