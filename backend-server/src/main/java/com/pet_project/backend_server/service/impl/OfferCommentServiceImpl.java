package com.pet_project.backend_server.service.impl;

import com.pet_project.backend_server.dto.request.DataTableRequest;
import com.pet_project.backend_server.dto.response.comment.CommentsResponse;
import com.pet_project.backend_server.entity.offer.OfferComments;
import com.pet_project.backend_server.repository.offer.OfferCommentsRepository;
import com.pet_project.backend_server.service.OfferCommentsService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OfferCommentServiceImpl implements OfferCommentsService {

    private final OfferCommentsRepository offerCommentsRepository;

    @Override
    public void create(OfferComments entity) {
        offerCommentsRepository.save(entity);
    }

    @Override
    public void update(OfferComments entity) {
        offerCommentsRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        offerCommentsRepository.deleteById(id);
    }

    @Override
    public OfferComments findById(Long id) {
        return offerCommentsRepository.findById(id).orElseThrow();
    }

    @Override
    public Page<OfferComments> findAll(DataTableRequest request) {
        return null;
    }

    @Override
    public List<CommentsResponse> findByOfferIdOrderByCreatedAtAsc(Long offerId) {
        List<OfferComments> comments = offerCommentsRepository.findByOfferIdOrderByCreatedAtAsc(offerId);
        return comments.stream()
                .map(this::mapToCommentsResponse)
                .toList();
    }

    private CommentsResponse mapToCommentsResponse(OfferComments offerComments){
        return CommentsResponse.builder()
                .id(offerComments.getId())
                .content(offerComments.getContent())
                .authorUsername(offerComments.getAuthorUsername().getUsername())
                .createdAt(offerComments.getCreatedAt())
                .build();

    }
}
