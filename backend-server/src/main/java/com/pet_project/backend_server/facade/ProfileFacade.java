package com.pet_project.backend_server.facade;

import com.pet_project.backend_server.dto.request.profileRequest.ProfileInformationRequest;
import com.pet_project.backend_server.dto.request.profileRequest.ProfileUpdateRequest;
import com.pet_project.backend_server.dto.response.dataResponse.DataDeleteResponse;
import com.pet_project.backend_server.dto.response.dataResponse.DataSavedResponse;
import com.pet_project.backend_server.dto.response.profileResponse.ProfileResponse;


public interface ProfileFacade {

    ProfileResponse getProfile(String username);
    DataSavedResponse addInformation(ProfileInformationRequest request, String username);
    DataSavedResponse updateProfile(ProfileUpdateRequest request);
    DataDeleteResponse deleteProfile(Long id);
}
