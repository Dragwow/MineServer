package com.pet_project.backend_server.dto.response.projectResponse;

import com.pet_project.backend_server.dto.response.ApiResponse;
import com.pet_project.backend_server.entity.project.Project;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectResponse extends ApiResponse<Long> {

        @Schema(description = "Name project")
        private String nameProject;

        @Schema(description = "Description")
        private String descriptionProject;

        @Schema(description = "CreatedBy")
        private String createdBy;

        @Schema(description = "Project link")
        private String linkGit;

        @Schema(description = "Created at")
        private LocalDateTime createdAt;

        public ProjectResponse(Project project) {
                project.setId(project.getId());
                this.nameProject = project.getNameProject();
                this.descriptionProject = project.getDescription();
                this.createdBy = project.getCreatedBy();
                this.linkGit = project.getLinkGit();
                this.createdAt = project.getCreatedAt();
        }

}
