package com.pet_project.backend_server.facade.impl;

import com.pet_project.backend_server.dto.request.comment.CommentsRequest;
import com.pet_project.backend_server.dto.response.comment.CommentsResponse;
import com.pet_project.backend_server.entity.offer.Offer;
import com.pet_project.backend_server.entity.offer.OfferComments;
import com.pet_project.backend_server.entity.user.User;
import com.pet_project.backend_server.facade.OfferCommentsFacade;
import com.pet_project.backend_server.service.OfferCommentsService;
import com.pet_project.backend_server.service.OfferService;
import com.pet_project.backend_server.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class OfferCommentsFacadeImpl implements OfferCommentsFacade {

    private final OfferCommentsService offerCommentsService;
    private final UserService userService;
    private final OfferService offerService;


    @Override
    public CommentsResponse addComment(CommentsRequest request, String username) {
        Offer offer = offerService.findById(request.getPostId());
        User user = userService.findByUsername(username);

        OfferComments offerComments = new OfferComments();
        offerComments.setOffer(offer);
        offerComments.setAuthorUsername(user);
        offerComments.setContent(request.getContent());
        offerComments.setCreatedAt(LocalDateTime.now());

        offerCommentsService.create(offerComments);

        return new CommentsResponse(offerComments);
    }

    @Override
    public List<CommentsResponse> getComments(Long offerId) {
        return offerCommentsService.findByOfferIdOrderByCreatedAtAsc(offerId);
    }



}
