package com.pet_project.backend_server.controller;

import com.pet_project.backend_server.dto.request.profileRequest.ProfileUpdateRequest;
import com.pet_project.backend_server.dto.response.dataResponse.DataDeleteResponse;
import com.pet_project.backend_server.dto.response.dataResponse.DataSavedResponse;
import com.pet_project.backend_server.dto.response.ResponseContainer;
import com.pet_project.backend_server.facade.OfferFacade;
import com.pet_project.backend_server.facade.ProjectFacade;
import com.pet_project.backend_server.facade.ProfileFacade;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Settings controller", description = "contains settings")
@RestController
@RequestMapping("/api/settings")
@AllArgsConstructor
public class SettingsController {

    private final OfferFacade offerFacade;
    private final ProjectFacade projectFacade;
    private final ProfileFacade profileFacade;

    @DeleteMapping("/deleteOffer")
    public ResponseEntity<ResponseContainer<DataDeleteResponse>> deleteOffer(
            @RequestParam
            Long id){
        offerFacade.delete(id);
        return ResponseEntity.ok(new ResponseContainer<>(new DataDeleteResponse()));
    }

    @DeleteMapping("/deleteProject")
    public ResponseEntity<ResponseContainer<DataDeleteResponse>> deleteProject(
            @RequestParam
            Long id){
        projectFacade.delete(id);
        return ResponseEntity.ok(new ResponseContainer<>(new DataDeleteResponse()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseContainer<DataDeleteResponse>> deleteProfile(
            @PathVariable
            Long id){
        profileFacade.deleteProfile(id);
        return  ResponseEntity.ok(new ResponseContainer<>(new DataDeleteResponse()));
    }

    @PatchMapping("/updateProfile")
    public ResponseEntity<ResponseContainer<DataSavedResponse>> updateProfile(
            @PathVariable Long id,
            @RequestBody
            ProfileUpdateRequest request){
        profileFacade.updateProfile(request);
        return ResponseEntity.ok(new ResponseContainer<>(new DataSavedResponse()));
    }
}
