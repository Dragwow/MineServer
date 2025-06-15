package com.pet_project.backend_server.dto.request.offerRequest;

import com.pet_project.backend_server.entity.offer.OfferStatus;
import com.pet_project.backend_server.entity.offer.OfferType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfferItemsRequest {

    private Long id;

    @NotBlank
    @Schema(description = "title")
    private String title;

    @NotBlank
    @Schema(description = "description")
    private String description;

    @NotNull
    @Schema(description = "status")
    @Enumerated(EnumType.STRING)
    private OfferStatus status;

    @NotNull
    @Schema(description = "type")
    @Enumerated(EnumType.STRING)
    private OfferType type;
}
