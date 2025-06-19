package com.pet_project.backend_server.service;

import com.pet_project.backend_server.entity.project.Project;
import com.pet_project.backend_server.entity.user.User;

import java.util.List;

public interface ProjectService extends CrudService<Project> {
    List<Project> getProjectByUserId(Long userId);
    List<Project> findByUser(User user);
    void deleteAllByUSerId(Long userId);
}
