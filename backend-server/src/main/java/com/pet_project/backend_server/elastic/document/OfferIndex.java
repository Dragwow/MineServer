package com.pet_project.backend_server.elastic.document;

import com.pet_project.backend_server.entity.offer.OfferStatus;
import com.pet_project.backend_server.entity.offer.OfferType;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Document(indexName = "offer_index")
@Builder(toBuilder = true)
public class OfferIndex {

    @Id
    private String offerId;

    @Field(type = FieldType.Text)
    private String title;

    @Field(type = FieldType.Text)
    private String description;

    @Field(type = FieldType.Keyword)
    private String createdBy;

    @Field(type = FieldType.Keyword)
    private OfferStatus status;

    @Field(type = FieldType.Keyword)
    private OfferType type;

    @Field(type = FieldType.Keyword)
    private String userProfileId;


}
