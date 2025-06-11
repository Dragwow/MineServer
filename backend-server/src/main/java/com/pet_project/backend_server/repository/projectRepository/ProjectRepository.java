package com.pet_project.backend_server.repository.projectRepository;

import com.pet_project.backend_server.entity.project.Project;
import com.pet_project.backend_server.entity.userProfile.UserProfile;
import com.pet_project.backend_server.repository.BaseRepository;
import com.pet_project.backend_server.repository.data.ProjectSearchDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends BaseRepository<Project> {
    List<Project> findByUserProfileId(Long userProfileId);
    void deleteByUserProfileId(Long userProfileId);
    List<Project> findByUserProfile(UserProfile userProfile);

    @Query(value = "select distinct new com.pet_project.backend_server.repository.data.ProjectSearchDto(pv) from Project pv")
    List<ProjectSearchDto> findAllProjectSearchDto();
}
