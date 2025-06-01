package com.pet_project.backend_server.controller;

import com.pet_project.backend_server.dto.request.offerRequest.OfferRequest;
import com.pet_project.backend_server.dto.request.profileRequest.ProfileInformationRequest;
import com.pet_project.backend_server.dto.request.projectRequest.ProjectRequest;
import com.pet_project.backend_server.dto.response.DataSavedResponse;
import com.pet_project.backend_server.dto.response.ProfileResponse;
import com.pet_project.backend_server.dto.response.ResponseContainer;
import com.pet_project.backend_server.facade.OfferFacade;
import com.pet_project.backend_server.facade.ProjectFacade;
import com.pet_project.backend_server.service.ProfileService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Profile controller", description = "contains profile method")
@RestController
@RequestMapping("/api/profile")
@AllArgsConstructor
@Validated
public class ProfileController {

    private final ProfileService profileService;
    private final OfferFacade offerFacade;
    private final ProjectFacade projectFacade;

    @GetMapping("/userProfile")
    public ResponseEntity<ResponseContainer<ProfileResponse>> getUserProfile() {
        return ResponseEntity.ok(new ResponseContainer<>(profileService.getProfile()));
    }

    @PostMapping("/addInf")
    public ResponseEntity<ResponseContainer<DataSavedResponse>> addInf(
            @RequestBody
            ProfileInformationRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new  ResponseContainer<>(profileService.addInformation(request)));
    }

    @PostMapping("/addProject")
    public ResponseEntity<ResponseContainer<DataSavedResponse>> addProject(
            @RequestBody
            ProjectRequest request){
        projectFacade.create(request);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseContainer<>(new DataSavedResponse()));
    }

    @PostMapping("/addOffer")
    public ResponseEntity<ResponseContainer<DataSavedResponse>> addOffer(
            @RequestBody
            OfferRequest request){
        offerFacade.create(request);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseContainer<>(new DataSavedResponse()));
    }
}
