package com.pet_project.backend_server.facade;

import com.pet_project.backend_server.dto.request.ApiRequest;
import com.pet_project.backend_server.dto.request.DataTableRequest;
import com.pet_project.backend_server.dto.response.ApiResponse;
import com.pet_project.backend_server.dto.response.DataTableResponse;

public interface CrudFacade <REQ extends ApiRequest, RES extends ApiResponse> {

    void create(REQ request);
    void update(REQ request, Long id);
    void delete(Long id);
    RES findById(Long id);
    DataTableResponse<RES> findAll(DataTableRequest request);
}
