package com.pet_project.backend_server.service.impl;

import com.pet_project.backend_server.elastic.document.OfferIndex;
import com.pet_project.backend_server.elastic.repository.OfferIndexRepository;
import com.pet_project.backend_server.service.OfferSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OfferSearchServiceImpl implements OfferSearchService {

    private final OfferIndexRepository offerIndexRepository;

    @Override
    public List<OfferIndex> searchByTitle(String title) {
        return offerIndexRepository.findByTitle(title);
    }
}
