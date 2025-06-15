package com.pet_project.backend_server.facade.impl;

import com.pet_project.backend_server.dto.request.DataTableRequest;
import com.pet_project.backend_server.dto.request.offerRequest.OfferItemsRequest;
import com.pet_project.backend_server.dto.request.offerRequest.OfferRequest;
import com.pet_project.backend_server.dto.request.projectRequest.ProjectRequestItem;
import com.pet_project.backend_server.dto.response.DataTableResponse;
import com.pet_project.backend_server.dto.response.offerResponse.OfferResponse;
import com.pet_project.backend_server.entity.offer.Offer;
import com.pet_project.backend_server.entity.project.Project;
import com.pet_project.backend_server.exception.EntityNotFoundException;
import com.pet_project.backend_server.facade.OfferFacade;
import com.pet_project.backend_server.service.OfferService;
import com.pet_project.backend_server.service.UserService;
import com.pet_project.backend_server.util.ExceptionUtil;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.security.access.AccessDeniedException;
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

        List<OfferItemsRequest> items = request.getOffers();

        if (items.isEmpty()) {
            throw new IllegalArgumentException("Offer list must not be empty");
        }

        List<Offer> offers = items.stream()
                .map(dto-> {
                    Offer offer = new Offer();
                    offer.setUser(user);
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
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        var user = userService.findByUsername(username);

        List<OfferItemsRequest> items = request.getOffers();

        if (items.isEmpty()){
            throw new IllegalArgumentException("Offer must not be empty");
        }

        for (OfferItemsRequest dto : items){
            if (dto.getId() == null){
                throw new IllegalArgumentException("offer ID must not be update");
            }

            Offer offer = offerService.findById(dto.getId())
                    .orElseThrow(() -> new EntityNotFoundException(ExceptionUtil.ENTITY_NOT_FOUND + dto.getId()));

            if (!offer.getUser().getId().equals(user.getId())) {
                throw new AccessDeniedException("You are not allowed to update to this project");
            }
            offer.setTitle(dto.getTitle());
            offer.setDescription(dto.getDescription());
            offer.setStatus(dto.getStatus());
            offer.setType(dto.getType());
            offer.setUpdatedAt(LocalDateTime.now());
        }
    }

    @Override
    public void delete(Long id) {
        offerService.delete(id);
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
