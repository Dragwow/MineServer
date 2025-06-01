package com.pet_project.backend_server.service;

import com.pet_project.backend_server.dto.request.DataTableRequest;
import com.pet_project.backend_server.entity.BaseEntity;
import org.springframework.data.domain.Page;

public interface CrudService<E extends BaseEntity> {

    void create(E entity);
    void update(E entity);
    void delete(Long id);
    E findById(Long id);
    Page<E> findAll(DataTableRequest request);
}
