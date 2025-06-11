package com.pet_project.backend_server.controller;

import com.pet_project.backend_server.dto.response.ResponseContainer;
import com.pet_project.backend_server.elastic.document.OfferIndex;
import com.pet_project.backend_server.entity.offer.OfferStatus;
import com.pet_project.backend_server.entity.offer.OfferType;
import com.pet_project.backend_server.service.OfferSearchService;
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
@RequestMapping("/api/searchOffer")
@AllArgsConstructor
public class SearchController {

    private final OfferSearchService offerSearchService;

    @GetMapping("/statusOffer")
    public ResponseEntity<ResponseContainer<List<OfferIndex>>> searchByStatus(@RequestParam OfferStatus status) {
        return ResponseEntity.ok(new ResponseContainer<>(offerSearchService.searchByStatus(status)));
    }

    @GetMapping("/typeOffer")
    public ResponseEntity<ResponseContainer<List<OfferIndex>>> searchByType(@RequestParam OfferType type) {
        return ResponseEntity.ok(new ResponseContainer<>(offerSearchService.searchByType(type)));
    }

    @GetMapping("/searchOffer")
    public ResponseEntity<ResponseContainer<List<OfferIndex>>> search(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String username) {
        return ResponseEntity.ok(new ResponseContainer<>(offerSearchService.search(title, description, username)));
    }
}
