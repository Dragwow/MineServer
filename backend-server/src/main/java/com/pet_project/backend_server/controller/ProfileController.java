package com.pet_project.backend_server.controller;

import com.pet_project.backend_server.dto.request.offerRequest.OfferRequest;
import com.pet_project.backend_server.dto.request.profileRequest.ProfileInformationRequest;
import com.pet_project.backend_server.dto.request.projectRequest.ProjectRequest;
import com.pet_project.backend_server.dto.response.dataResponse.DataSavedResponse;
import com.pet_project.backend_server.dto.response.profileResponse.ProfileResponse;
import com.pet_project.backend_server.dto.response.ResponseContainer;
import com.pet_project.backend_server.facade.OfferFacade;
import com.pet_project.backend_server.facade.ProjectFacade;
import com.pet_project.backend_server.facade.ProfileFacade;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@Tag(name = "Profile controller", description = "contains profile method")
@RestController
@RequestMapping("/api/profile")
@AllArgsConstructor
@Validated
public class ProfileController {

    private final ProfileFacade profileFacade;
    private final OfferFacade offerFacade;
    private final ProjectFacade projectFacade;

    @GetMapping("/${username}")
    public ResponseEntity<ResponseContainer<ProfileResponse>> getUserProfile(
            Principal principal
    ) {
        return ResponseEntity.ok(new ResponseContainer<>(profileFacade.getProfile(principal.getName())));
    }

    @PostMapping("/add-project")
    public ResponseEntity<ResponseContainer<DataSavedResponse>> addProject(
            @RequestBody
            ProjectRequest request){
        projectFacade.create(request);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseContainer<>(new DataSavedResponse()));
    }

    @PostMapping("/add-offer")
    public ResponseEntity<ResponseContainer<DataSavedResponse>> addOffer(
            @RequestBody
            OfferRequest request){
        offerFacade.create(request);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseContainer<>(new DataSavedResponse()));
    }

    @PostMapping("/update-offer")
    public ResponseEntity<ResponseContainer<DataSavedResponse>> updateOffer(
            @RequestBody
            OfferRequest request,
            Long id){
        offerFacade.update(request, id);
        return  ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseContainer<>(new DataSavedResponse()));
    }

    @PostMapping("/update-project")
    public ResponseEntity<ResponseContainer<DataSavedResponse>> updateProject(
            @RequestBody
            ProjectRequest request,
            Long id){
        projectFacade.update(request, id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseContainer<>(new DataSavedResponse()));
    }

    @PostMapping("/upload-avatar")
    public ResponseEntity<ResponseContainer<String>> uploadAvatar(
            @RequestParam("file")
            MultipartFile file,
            Principal principal){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseContainer<>(profileFacade.uploadAvatar(file, principal.getName())));

    }

}
