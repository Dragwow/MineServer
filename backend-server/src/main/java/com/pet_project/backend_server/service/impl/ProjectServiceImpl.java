package com.pet_project.backend_server.service.impl;

import com.pet_project.backend_server.dto.request.DataTableRequest;
import com.pet_project.backend_server.entity.project.Project;
import com.pet_project.backend_server.entity.user.User;
import com.pet_project.backend_server.repository.projectRepository.ProjectRepository;
import com.pet_project.backend_server.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        projectRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        projectRepository.deleteById(id);
    }

    @Override
    public Optional<Project> findById(Long id) {
        return projectRepository.findById(id);
    }

    @Override
    public Page<Project> findAll(DataTableRequest request) {
        Sort sort = Sort.by(
                request.getOrder().equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC,
                request.getSort());
        Pageable pageable = PageRequest.of(request.getPage() -1, request.getSize(), sort);
        return projectRepository.findAll(pageable);
    }

    @Override
    public List<Project> getProjectByUserId(Long userId) {
        return projectRepository.findByUserId(userId);
    }

    @Override
    public List<Project> findByUser(User user) {
        return projectRepository.findByUser(user);
    }
}
