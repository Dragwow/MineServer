package com.pet_project.backend_server.dto.request.offerRequest;

import com.pet_project.backend_server.dto.request.ApiRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OfferRequest extends ApiRequest {

    private List<OfferItemsRequest> offers;
}
