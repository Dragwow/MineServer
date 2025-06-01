package com.pet_project.backend_server.service;

import com.pet_project.backend_server.entity.offer.Offer;
import com.pet_project.backend_server.entity.userProfile.UserProfile;

import java.util.List;

public interface OfferService extends CrudService<Offer> {
    List<Offer> getOfferByUserProfileId(Long userProfileId);
    List<Offer> findByUserProfile(UserProfile userProfile);
}
