package com.pet_project.backend_server.repository.version;

import com.pet_project.backend_server.entity.version.ApiVersion;
import com.pet_project.backend_server.repository.BaseRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ApiVersionRepository extends BaseRepository<ApiVersion> {

    ApiVersion findTop1ByOrderByIdDesc();
}