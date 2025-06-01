package com.pet_project.backend_server.store;

import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@Document(collection = "saved_queries")
public class SavedQuery {

    @MongoId(FieldType.OBJECT_ID)
    private String id;

    @Indexed
    private String query;

    public SavedQuery(String query) {
        this.query = query;
    }
}
