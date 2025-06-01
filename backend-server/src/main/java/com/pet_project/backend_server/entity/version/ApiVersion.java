package com.pet_project.backend_server.entity.version;

import com.pet_project.backend_server.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "api_versions")
public class ApiVersion extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String version;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    public ApiVersion() {
        this.created = new Date();
    }
}
