package com.pet_project.backend_server.elastic.repository;

import com.pet_project.backend_server.elastic.document.OfferIndex;
import com.pet_project.backend_server.entity.offer.OfferStatus;
import com.pet_project.backend_server.entity.offer.OfferType;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferIndexRepository extends ElasticsearchRepository<OfferIndex, String> {

    List<OfferIndex> findByStatus(OfferStatus status);
    List<OfferIndex> findByType(OfferType type);

}
