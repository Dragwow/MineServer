package com.pet_project.backend_server.repository.itLanguage;

import com.pet_project.backend_server.entity.itLanguage.ItLanguage;
import com.pet_project.backend_server.entity.user.User;
import com.pet_project.backend_server.repository.BaseRepository;
import com.pet_project.backend_server.repository.data.ItLanguageSearchDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItLanguageRepository extends BaseRepository<ItLanguage> {

    List<ItLanguage> findByUserId(Long userId);
    void deleteAllByUserId(Long userId);
    List<ItLanguage> findByUser(User user);

    @Query(value = "select distinct new com.pet_project.backend_server.repository.data.ItLanguageSearchDto(pv, pv.languageType, pv.languageLevel) from ItLanguage pv")
    List<ItLanguageSearchDto> findAllItLanguageSearchDto();

  }
