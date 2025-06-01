package com.pet_project.backend_server.service;

public interface ApiVersionService {

    String getApiVersion();
    boolean checkApiVersion(String apiVersion);
}
