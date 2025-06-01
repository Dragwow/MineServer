package com.pet_project.backend_server.elastic.repository;

import com.pet_project.backend_server.elastic.document.QuerySearch;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuerySearchRepository extends ElasticsearchRepository<QuerySearch, String> {
}
