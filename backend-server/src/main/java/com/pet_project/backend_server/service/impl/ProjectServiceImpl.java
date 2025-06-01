package com.pet_project.backend_server.service.impl;

import com.pet_project.backend_server.dto.request.DataTableRequest;
import com.pet_project.backend_server.entity.project.Project;
import com.pet_project.backend_server.entity.userProfile.UserProfile;
import com.pet_project.backend_server.repository.projectRepository.ProjectRepository;
import com.pet_project.backend_server.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private ProjectRepository projectRepository;

    @Override
    public void create(Project project) {
        projectRepository.save(project);
    }

    @Override
    public void update(Project entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Project findById(Long id) {
        return null;
    }

    @Override
    public Page<Project> findAll(DataTableRequest request) {
        return null;
    }

    @Override
    public List<Project> getProjectByUserProfileId(Long userProfileId) {
        return projectRepository.findByUserProfileId(userProfileId);
    }

    @Override
    public List<Project> findByUserProfile(UserProfile userProfile) {
        return projectRepository.findByUserProfile(userProfile);
    }
}
