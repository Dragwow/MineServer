package com.pet_project.backend_server.service;

import com.pet_project.backend_server.elastic.document.OfferIndex;
import com.pet_project.backend_server.entity.offer.OfferStatus;
import com.pet_project.backend_server.entity.offer.OfferType;

import java.util.List;

public interface OfferSearchService {

    List<OfferIndex> searchByStatus(OfferStatus status);
    List<OfferIndex> searchByType(OfferType type);
    List<OfferIndex> searchByTitle(String text);
    List<OfferIndex> searchBYDescription(String text);
    List<OfferIndex> searchByUsername(String text);

}
