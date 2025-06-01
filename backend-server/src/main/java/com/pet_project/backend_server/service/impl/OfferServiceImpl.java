package com.pet_project.backend_server.service.impl;

import com.pet_project.backend_server.dto.request.DataTableRequest;
import com.pet_project.backend_server.entity.offer.Offer;
import com.pet_project.backend_server.entity.userProfile.UserProfile;
import com.pet_project.backend_server.repository.offer.OfferRepository;
import com.pet_project.backend_server.service.OfferService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;

    @Override
    public void create(Offer offer) {
        offerRepository.save(offer);
    }

    @Override
    public void update(Offer entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Offer findById(Long id) {
        return null;
    }

    @Override
    public List<Offer> getOfferByUserProfileId(Long userProfileId) {
        return offerRepository.findByUserProfileId(userProfileId);
    }

    @Override
    public List<Offer> findByUserProfile(UserProfile userProfile) {
        return offerRepository.findByUserProfile(userProfile);
    }

    @Override
    public Page<Offer> findAll(DataTableRequest request) {
        Sort sort = Sort.by(
                request.getOrder().equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC,
                request.getSort());
        Pageable pageable = PageRequest.of(request.getPage() -1, request.getSize(), sort);
        return offerRepository.findAll(pageable);
    }
}
