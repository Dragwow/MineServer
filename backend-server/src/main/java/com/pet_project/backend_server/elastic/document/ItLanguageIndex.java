package com.pet_project.backend_server.elastic.document;

import com.pet_project.backend_server.entity.itLanguage.LanguageLevel;
import com.pet_project.backend_server.entity.itLanguage.ProgrammingLanguageType;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;



@Data
@Document(indexName = "it_language_index")
@Builder(toBuilder = true)
public class ItLanguageIndex {

    @Id
    private String itLanguageId;

    @Field(type = FieldType.Keyword)
    private LanguageLevel languageLevel;

    @Field(type = FieldType.Keyword)
    private ProgrammingLanguageType languageType;
}
