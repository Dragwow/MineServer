package com.pet_project.backend_server.repository.itLanguage;

import com.pet_project.backend_server.entity.itLanguage.ItLanguage;
import com.pet_project.backend_server.entity.userProfile.UserProfile;
import com.pet_project.backend_server.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItLanguageRepository extends BaseRepository<ItLanguage> {

    List<ItLanguage> findByUserProfileId(Long userProfileId);
    void deleteByUserProfileId(Long userProfileId);
    List<ItLanguage> findByUserProfile(UserProfile userProfile);

  }
