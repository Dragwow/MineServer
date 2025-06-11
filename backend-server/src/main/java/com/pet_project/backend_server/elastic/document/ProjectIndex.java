package com.pet_project.backend_server.elastic.document;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Document(indexName = "project_index")
@Builder(toBuilder = true)
public class ProjectIndex {

    @Id
    private String projectId;

    @Field(type = FieldType.Text)
    private String projectName;

    @Field(type = FieldType.Text)
    private String description;

    @Field(type = FieldType.Keyword)
    private String createdBy;
}
