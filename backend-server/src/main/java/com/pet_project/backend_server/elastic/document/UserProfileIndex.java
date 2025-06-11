package com.pet_project.backend_server.elastic.document;

import com.pet_project.backend_server.entity.itLanguage.ItLanguage;
import com.pet_project.backend_server.entity.language.Language;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;



@Getter
@Setter
@Document(indexName = "profile_index")
@Builder(toBuilder = true)
public class UserProfileIndex {

    @Id
    private String profileId;

    @Field(type = FieldType.Text)
    private String bio;

    @Field(type = FieldType.Text)
    private String github;

    @Field(type = FieldType.Text)
    private List<ItLanguage> itLanguage;

    @Field(type = FieldType.Text)
    private List<Language> language;

    @Field(type = FieldType.Keyword)
    private String username;

    @Field(type = FieldType.Keyword)
    private String firstName;

    @Field(type = FieldType.Keyword)
    private String lastName;

    @Field(type = FieldType.Text)
    private int age;
}
