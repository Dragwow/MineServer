package com.pet_project.backend_server.controller;

import com.pet_project.backend_server.dto.response.ResponseContainer;
import com.pet_project.backend_server.elastic.document.OfferIndex;
import com.pet_project.backend_server.elastic.document.ProjectIndex;
import com.pet_project.backend_server.elastic.document.UserIndex;
import com.pet_project.backend_server.entity.offer.OfferStatus;
import com.pet_project.backend_server.entity.offer.OfferType;
import com.pet_project.backend_server.service.OfferSearchService;
import com.pet_project.backend_server.service.ProjectSearchService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Offer search controller", description = "contains posts method")
@RestController
@RequestMapping("/api/search")
@AllArgsConstructor
public class SearchController {

    private final OfferSearchService offerSearchService;
    private final ProjectSearchService projectSearchService;

    @GetMapping("/statusOffer")
    public ResponseEntity<ResponseContainer<List<OfferIndex>>> searchByStatus(@RequestParam OfferStatus status) {
        return ResponseEntity.ok(new ResponseContainer<>(offerSearchService.searchByStatus(status)));
    }

    @GetMapping("/typeOffer")
    public ResponseEntity<ResponseContainer<List<OfferIndex>>> searchByType(@RequestParam OfferType type) {
        return ResponseEntity.ok(new ResponseContainer<>(offerSearchService.searchByType(type)));
    }

    @GetMapping("/titleOffer")
    public ResponseEntity<ResponseContainer<List<OfferIndex>>> searchByTitle(@RequestParam String text) {
        return ResponseEntity.ok(new ResponseContainer<>(offerSearchService.searchByTitle(text)));
    }

    @GetMapping("/descriptionOffer")
    public ResponseEntity<ResponseContainer<List<OfferIndex>>> searchByDescription(@RequestParam String text) {
        return ResponseEntity.ok(new ResponseContainer<>(offerSearchService.searchBYDescription(text)));
    }

    @GetMapping("/usernameOffer")
    public ResponseEntity<ResponseContainer<List<OfferIndex>>> searchByUsername(@RequestParam String text) {
        return ResponseEntity.ok(new ResponseContainer<>(offerSearchService.searchByUsername(text)));
    }

//    @GetMapping("/usernameProfile")
//    public ResponseEntity<ResponseContainer<List<UserIndex>>> searchProfileByUsername(@RequestParam String text) {
//        return ResponseEntity.ok(new ResponseContainer<>(userProfileSearchService.searchProfileByUsername(text)));
//    }
//
//    @GetMapping("/firstNameProfile")
//    public ResponseEntity<ResponseContainer<List<UserIndex>>> searchProfileByFirstName(@RequestParam String text) {
//        return ResponseEntity.ok(new ResponseContainer<>(userProfileSearchService.searchProfileByFirstName(text)));
//    }
//
//    @GetMapping("/lastNameProfile")
//    public ResponseEntity<ResponseContainer<List<UserIndex>>> searchProfileByLastname(@RequestParam String text) {
//        return ResponseEntity.ok(new ResponseContainer<>(userProfileSearchService.searchProfileByLastName(text)));
//    }

    @GetMapping("/nameProject")
    public ResponseEntity<ResponseContainer<List<ProjectIndex>>> searchByProjectName(@RequestParam String text) {
        return ResponseEntity.ok(new ResponseContainer<>(projectSearchService.searchByProjectName(text)));
    }

    @GetMapping("/descriptionProject")
    public ResponseEntity<ResponseContainer<List<ProjectIndex>>> searchProjectByDescription(@RequestParam String text) {
        return ResponseEntity.ok(new ResponseContainer<>(projectSearchService.searchProjectByDescription(text)));
    }

    @GetMapping("/usernameProject")
    public ResponseEntity<ResponseContainer<List<ProjectIndex>>> searchProjectByUsername(@RequestParam String text) {
        return ResponseEntity.ok(new ResponseContainer<>(projectSearchService.searchProjectByUsername(text)));
    }




}
