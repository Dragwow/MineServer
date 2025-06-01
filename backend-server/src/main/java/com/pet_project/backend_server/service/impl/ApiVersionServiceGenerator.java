package com.pet_project.backend_server.service.impl;

import com.pet_project.backend_server.entity.version.ApiVersion;
import com.pet_project.backend_server.repository.version.ApiVersionRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApiVersionServiceGenerator {

    private final ApiVersionRepository apiVersionRepository;

    @PostConstruct
    public void generate() {
        List<ApiVersion> apiVersions = apiVersionRepository.findAll();
        ApiVersion apiVersion = new ApiVersion();
        if (apiVersions.isEmpty()) {
            apiVersion.setVersion(UUID.randomUUID().toString());
        }else {
            Set<String> versions = apiVersions
                    .stream()
                    .map(ApiVersion::getVersion)
                    .collect(Collectors.toSet());
            apiVersion.setVersion(generateApiVersion(versions));
        }
        apiVersionRepository.save(apiVersion);
    }

    private String generateApiVersion(final Set<String> apiVersions) {
        String apiVersion = UUID.randomUUID().toString();
        if (apiVersions.stream().anyMatch(apiVersion::equals)) {
            return generateApiVersion(apiVersions);
        }
        return apiVersion;
    }


}
