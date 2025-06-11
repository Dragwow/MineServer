package com.pet_project.backend_server.repository.language;

import com.pet_project.backend_server.entity.language.Language;
import com.pet_project.backend_server.entity.user.User;
import com.pet_project.backend_server.repository.BaseRepository;
import com.pet_project.backend_server.repository.data.LanguageSearchDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LanguageRepository extends BaseRepository<Language> {
    List<Language> findByUserId(Long userId);
    void deleteAllByUserId(Long userId);
    List<Language> findByUser(User user);

    @Query(value = "select distinct new com.pet_project.backend_server.repository.data.LanguageSearchDto(pv, pv.languageType, pv.languageLevel) from Language pv")
    List<LanguageSearchDto> findAllLanguageSearchDto();

}
