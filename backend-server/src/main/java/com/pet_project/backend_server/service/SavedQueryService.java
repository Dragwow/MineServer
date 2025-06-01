package com.pet_project.backend_server.service;

import com.pet_project.backend_server.dto.request.DataTableRequest;
import com.pet_project.backend_server.store.SavedQuery;
import org.springframework.data.domain.Page;

public interface SavedQueryService {

    void save(String searchText);
    Page<SavedQuery> findAll(DataTableRequest request);
}
