package com.pet_project.backend_server.controller;

import com.pet_project.backend_server.dto.request.DataTableRequest;
import com.pet_project.backend_server.dto.response.DataTableResponse;
import com.pet_project.backend_server.dto.response.ResponseContainer;
import com.pet_project.backend_server.dto.response.offerResponse.OfferResponse;
import com.pet_project.backend_server.elastic.document.OfferIndex;
import com.pet_project.backend_server.entity.offer.OfferStatus;
import com.pet_project.backend_server.entity.offer.OfferType;
import com.pet_project.backend_server.facade.OfferFacade;
import com.pet_project.backend_server.service.OfferSearchService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Offer page controller", description = "contains posts method")
@RestController
@RequestMapping("/api/offersPage")
@AllArgsConstructor
public class OffersPageController {

    private final OfferFacade offerFacade;


    @GetMapping("/offers")
    public ResponseEntity<ResponseContainer<DataTableResponse<OfferResponse>>> findAllOffers(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "title") String sort,
            @RequestParam(defaultValue = "desc") String order) {
        DataTableRequest request = new DataTableRequest(page, size, sort, order);
        return ResponseEntity.ok(new ResponseContainer<>(offerFacade.findAll(request)));
    }

}