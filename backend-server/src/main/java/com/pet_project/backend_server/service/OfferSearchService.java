package com.pet_project.backend_server.service;

import com.pet_project.backend_server.elastic.document.OfferIndex;

import java.util.List;

public interface OfferSearchService {

    List<OfferIndex> searchByTitle(String title);
}
