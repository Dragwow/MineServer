package com.pet_project.backend_server.repository.offer;

import com.pet_project.backend_server.entity.offer.Offer;
import com.pet_project.backend_server.entity.userProfile.UserProfile;
import com.pet_project.backend_server.repository.BaseRepository;
import com.pet_project.backend_server.repository.data.OfferSearchDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends BaseRepository<Offer> {

    List<Offer> findByUserProfileId(Long userProfileId);
    void deleteByUserProfileId(Long userProfileId);
    List<Offer> findByUserProfile(UserProfile userProfile);

    @Query(value = "select distinct new com.pet_project.backend_server.repository.data.OfferSearchDto(pv, pv.type) from Offer pv")
    List<OfferSearchDto> findAllOfferSearchDtoList();

}
