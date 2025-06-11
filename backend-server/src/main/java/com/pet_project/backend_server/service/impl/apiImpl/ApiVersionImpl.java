package com.pet_project.backend_server.service.impl.apiImpl;

import com.pet_project.backend_server.entity.version.ApiVersion;
import com.pet_project.backend_server.repository.version.ApiVersionRepository;
import com.pet_project.backend_server.service.ApiVersionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApiVersionImpl implements ApiVersionService {

    private final ApiVersionRepository apiVersionRepository;

    @Override
    public String getApiVersion() {
        ApiVersion apiVersion = apiVersionRepository.findTop1ByOrderByIdDesc();
        return apiVersion.getVersion();
    }

    @Override
    public boolean checkApiVersion(String apiVersion) {
        ApiVersion apiVersion1 = apiVersionRepository.findTop1ByOrderByIdDesc();
        return apiVersion1.getVersion().equals(apiVersion);
    }
}
