package com.pet_project.backend_server.elastic.repository;

import com.pet_project.backend_server.elastic.document.UserIndex;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserIndexRepository extends ElasticsearchRepository<UserIndex, Long> {

    List<UserIndex> searchProfileByUsername(String username);
    List<UserIndex> searchProfileByLastName(String lastName);
    List<UserIndex> searchProfileByFirstName(String firsName);
}
