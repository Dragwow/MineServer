package com.pet_project.backend_server.service;

import com.pet_project.backend_server.dto.request.profileRequest.ProfileInformationRequest;
import com.pet_project.backend_server.dto.response.DataSavedResponse;
import com.pet_project.backend_server.dto.response.ProfileResponse;


public interface ProfileService {

    ProfileResponse getProfile();
    DataSavedResponse addInformation(ProfileInformationRequest request);
}
