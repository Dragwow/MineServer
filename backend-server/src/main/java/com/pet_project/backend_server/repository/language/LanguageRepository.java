package com.pet_project.backend_server.repository.language;

import com.pet_project.backend_server.entity.language.Language;
import com.pet_project.backend_server.entity.userProfile.UserProfile;
import com.pet_project.backend_server.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LanguageRepository extends BaseRepository<Language> {
    List<Language> findByUserProfileId(Long userProfileId);
    void deleteByUserProfileId(Long userProfileId);
    List<Language> findByUserProfile(UserProfile userProfile);

}
