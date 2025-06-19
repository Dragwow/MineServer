package com.pet_project.backend_server.service;

import com.pet_project.backend_server.dto.request.DataTableRequest;
import com.pet_project.backend_server.entity.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.EntityGraph;

import java.nio.file.AccessDeniedException;
import java.util.Optional;

public interface CrudService<E extends BaseEntity> {

    void create(E entity);
    void update(E entity) ;
    void delete(Long id);
    E findById(Long id);
    Page<E> findAll(DataTableRequest request);
}
