package com.pet_project.backend_server.service;

import com.pet_project.backend_server.entity.project.Project;
import com.pet_project.backend_server.entity.userProfile.UserProfile;

import java.util.List;

public interface ProjectService extends CrudService<Project> {
    List<Project> getProjectByUserProfileId(Long userProfileId);
    List<Project> findByUserProfile(UserProfile userProfile);
}
