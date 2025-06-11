package com.pet_project.backend_server.service.impl;

import com.pet_project.backend_server.elastic.document.OfferIndex;
import com.pet_project.backend_server.elastic.repository.OfferIndexRepository;
import com.pet_project.backend_server.entity.offer.OfferStatus;
import com.pet_project.backend_server.entity.offer.OfferType;
import com.pet_project.backend_server.service.OfferSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OfferSearchServiceImpl implements OfferSearchService {

    private final OfferIndexRepository offerIndexRepository;

    @Override
    public List<OfferIndex> searchByStatus(OfferStatus status){
        return offerIndexRepository.findByStatus(status);
    }

    @Override
    public List<OfferIndex> searchByType(OfferType type){
        return offerIndexRepository.findByType(type);
    }

    @Override
    public List<OfferIndex> searchByTitle(String text){
        return offerIndexRepository.findByTitle(text);

    }

    @Override
    public List<OfferIndex> searchBYDescription(String text) {
        return offerIndexRepository.findByDescription(text);
    }

    @Override
    public List<OfferIndex> searchByUsername(String text) {
        return offerIndexRepository.findByCreatedBy(text);
    }
}
