package com.pet_project.backend_server.service;

import com.pet_project.backend_server.entity.offer.Offer;
import com.pet_project.backend_server.entity.user.User;

import java.util.List;

public interface OfferService extends CrudService<Offer> {
    List<Offer> getOfferByUserId(Long userId);
    List<Offer> findByUser(User user);
    void deleteAllByUserId(Long userId);
}
