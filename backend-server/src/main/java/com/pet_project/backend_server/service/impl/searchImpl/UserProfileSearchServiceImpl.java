package com.pet_project.backend_server.service.impl.searchImpl;

import com.pet_project.backend_server.elastic.document.UserProfileIndex;
import com.pet_project.backend_server.service.UserProfileSearchService;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.client.erhlc.NativeSearchQuery;
import org.springframework.data.elasticsearch.client.erhlc.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserProfileSearchServiceImpl implements UserProfileSearchService {

    private final ElasticsearchOperations elasticsearchOperations;

    @Override
    public List<UserProfileIndex> searchProfile(String username, String firstName, String lastName) {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();

        if (username != null && !username.isEmpty()) {
            queryBuilder.must(QueryBuilders.matchQuery("username", username));
        }

        if (firstName != null && !firstName.isEmpty()) {
            queryBuilder.must(QueryBuilders.matchQuery("firstName", firstName));
        }

        if (lastName != null && !lastName.isEmpty()) {
            queryBuilder.must(QueryBuilders.matchQuery("lastName", lastName));
        }

        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .build();

        return elasticsearchOperations
                .search(searchQuery, UserProfileIndex.class)
                .map(SearchHit::getContent)
                .toList();
    }
}
