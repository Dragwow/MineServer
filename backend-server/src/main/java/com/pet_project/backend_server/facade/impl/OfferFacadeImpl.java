package com.pet_project.backend_server.facade.impl;

import com.pet_project.backend_server.dto.request.DataTableRequest;
import com.pet_project.backend_server.dto.request.offerRequest.OfferItemsRequest;
import com.pet_project.backend_server.dto.request.offerRequest.OfferRequest;
import com.pet_project.backend_server.dto.response.DataTableResponse;
import com.pet_project.backend_server.dto.response.offerResponse.OfferResponse;
import com.pet_project.backend_server.entity.offer.Offer;
import com.pet_project.backend_server.entity.userProfile.UserProfile;
import com.pet_project.backend_server.facade.OfferFacade;
import com.pet_project.backend_server.service.OfferService;
import com.pet_project.backend_server.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@AllArgsConstructor
public class OfferFacadeImpl implements OfferFacade {

    private final UserService userService;
    private final OfferService offerService;

    @Override
    public void create(OfferRequest request){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        var user = userService.findByUsername(username);
        UserProfile userProfile = user.getProfile();

        List<OfferItemsRequest> items = request.getOffers();

        if (items.isEmpty()) {
            throw new IllegalArgumentException("Offer list must not be empty");
        }

        List<Offer> offers = items.stream()
                .map(dto-> {
                    Offer offer = new Offer();
                    offer.setUserProfile(userProfile);
                    offer.setCreatedBy(username);
                    offer.setTitle(dto.getTitle());
                    offer.setDescription(dto.getDescription());
                    offer.setStatus(dto.getStatus());
                    offer.setType(dto.getType());
                    offer.setCreatedAt(LocalDateTime.now());
                    return offer;
                }).toList();
        offers.forEach(offerService::create);

    }

    @Override
    public void update(OfferRequest request, Long id) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public OfferResponse findById(Long id) {
        return null;
    }

    @Override
    @Cacheable(value = "offersCache",
            key = "#request.page + '-' + #request.size + '-' + #request.sort + '-' + #request.order")
    public DataTableResponse<OfferResponse> findAll(DataTableRequest request) {
        Page<Offer> page = offerService.findAll(request);
        DataTableResponse<OfferResponse> dataTableResponse = new DataTableResponse<>();
        dataTableResponse.setSort(request.getSort());
        dataTableResponse.setOrder(request.getOrder());
        List<OfferResponse> offerResponseList = page.getContent()
                .stream()
                .map(OfferResponse::new)
                .toList();
        dataTableResponse.setItems(offerResponseList);
        return dataTableResponse;
    }



}
