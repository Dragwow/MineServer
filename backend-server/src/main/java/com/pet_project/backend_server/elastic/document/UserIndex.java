package com.pet_project.backend_server.elastic.document;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Data
@Document(indexName = "uses_index")
@Builder(toBuilder = true)
public class UserIndex {

    @Id
    private String userId;

    @Field(type = FieldType.Keyword)
    private String username;

    @Field(type = FieldType.Text)
    private String firstName;

    @Field(type = FieldType.Text)
    private String lastName;

    @Field(type = FieldType.Integer)
    private int age;

    @Field(type = FieldType.Text)
    private String bio;

    @Field(type = FieldType.Text)
    private String github;

    @Field(type = FieldType.Text)
    private List<ItLanguageIndex> itLanguages;

    @Field(type = FieldType.Text)
    private List<LanguageIndex> languages;
}
