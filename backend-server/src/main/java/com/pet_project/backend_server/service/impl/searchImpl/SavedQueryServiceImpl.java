package com.pet_project.backend_server.service.impl.searchImpl;

import com.pet_project.backend_server.dto.request.DataTableRequest;
import com.pet_project.backend_server.service.SavedQueryService;
import com.pet_project.backend_server.store.SavedQuery;
import com.pet_project.backend_server.store.SavedQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SavedQueryServiceImpl implements SavedQueryService {

    private final SavedQueryRepository savedQueryRepository;

    @Override
    public void save(String searchText) {
        SavedQuery savedQuery = new SavedQuery(searchText);
        savedQueryRepository.save(savedQuery);
    }

    @Override
    public Page<SavedQuery> findAll(DataTableRequest request) {
        Sort sort = Sort.by(
                request.getOrder().equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC,
                request.getSort());
        Pageable pageable = PageRequest.of(request.getPage() - 1, request.getSize(), sort);
        return savedQueryRepository.findAll(pageable);
    }
}
