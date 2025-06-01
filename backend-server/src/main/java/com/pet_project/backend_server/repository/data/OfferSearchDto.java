package com.pet_project.backend_server.repository.data;

import com.pet_project.backend_server.entity.offer.Offer;
import com.pet_project.backend_server.entity.offer.OfferType;

public record OfferSearchDto(Offer offer, OfferType type) {
}
