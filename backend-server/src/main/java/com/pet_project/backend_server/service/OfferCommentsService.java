package com.pet_project.backend_server.service;

import com.pet_project.backend_server.dto.request.comment.CommentsRequest;
import com.pet_project.backend_server.dto.response.comment.CommentsResponse;
import com.pet_project.backend_server.entity.offer.OfferComments;

import java.util.List;

public interface OfferCommentsService extends CrudService<OfferComments> {
    List<CommentsResponse> findByOfferIdOrderByCreatedAtAsc(Long offerId);
}
