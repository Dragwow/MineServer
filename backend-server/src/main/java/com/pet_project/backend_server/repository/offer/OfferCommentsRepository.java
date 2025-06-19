package com.pet_project.backend_server.repository.offer;

import com.pet_project.backend_server.entity.offer.OfferComments;
import com.pet_project.backend_server.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferCommentsRepository extends BaseRepository<OfferComments> {
    List<OfferComments> findByOfferIdOrderByCreatedAtAsc(Long offerId);
}
