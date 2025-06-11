package com.pet_project.backend_server.repository.offer;

import com.pet_project.backend_server.entity.offer.Offer;
import com.pet_project.backend_server.entity.user.User;
import com.pet_project.backend_server.repository.BaseRepository;
import com.pet_project.backend_server.repository.data.OfferSearchDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends BaseRepository<Offer> {

    List<Offer> findByUserId(Long userId);
    void deleteByUserId(Long userProfileId);
    List<Offer> findByUser(User user);

    @Query(value = "select distinct new com.pet_project.backend_server.repository.data.OfferSearchDto(pv, pv.type) from Offer pv")
    List<OfferSearchDto> findAllOfferSearchDtoList();

}
