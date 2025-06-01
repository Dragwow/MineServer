package com.pet_project.backend_server.dto.response.offerResponse;

import com.pet_project.backend_server.dto.response.ApiResponse;
import com.pet_project.backend_server.entity.offer.Offer;
import com.pet_project.backend_server.entity.offer.OfferStatus;
import com.pet_project.backend_server.entity.offer.OfferType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OfferResponse extends ApiResponse<Long> {

    @Schema(description = "title")
    private String title;

    @Schema(description = "created by")
    private String createdBy;

    @Schema(description = "description")
    private String description;

    @Schema(description = "status")
    private OfferStatus status;

    @Schema(description = "type")
    private OfferType type;

    @Schema(description = "created at")
    private LocalDateTime createdAt;

    public OfferResponse(Offer offer) {
        this.title = offer.getTitle();
        this.createdBy = offer.getCreatedBy();
        this.description = offer.getDescription();
        this.status = offer.getStatus();
        this.type = offer.getType();
        this.createdAt = offer.getCreatedAt();
    }
}
