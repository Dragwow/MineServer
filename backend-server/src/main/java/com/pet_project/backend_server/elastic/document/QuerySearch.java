package com.pet_project.backend_server.elastic.document;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Document(indexName = "query_searches")
public class QuerySearch {

    @Id
    private String id;

    @Field(name = "query", type = FieldType.Text)
    private String query;
}
