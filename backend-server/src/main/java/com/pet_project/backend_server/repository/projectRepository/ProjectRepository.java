package com.pet_project.backend_server.repository.projectRepository;

import com.pet_project.backend_server.entity.project.Project;
import com.pet_project.backend_server.entity.user.User;
import com.pet_project.backend_server.repository.BaseRepository;
import com.pet_project.backend_server.repository.data.ProjectSearchDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends BaseRepository<Project> {
    List<Project> findByUserId(Long userId);
    void deleteAllByUserId(Long userId);
    List<Project> findByUser(User user);

    @Query(value = "select distinct new com.pet_project.backend_server.repository.data.ProjectSearchDto(pv) from Project pv")
    List<ProjectSearchDto> findAllProjectSearchDto();
}
