package com.pet_project.backend_server.elastic.document;

import com.pet_project.backend_server.entity.itLanguage.LanguageLevel;
import com.pet_project.backend_server.entity.language.LanguageType;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Document(indexName = "language_index")
@Builder(toBuilder = true)
public class LanguageIndex {

    @Id
    private String languageId;

    @Field(type = FieldType.Keyword)
    private LanguageLevel languageLevel;

    @Field(type = FieldType.Keyword)
    private LanguageType languageType;
}
