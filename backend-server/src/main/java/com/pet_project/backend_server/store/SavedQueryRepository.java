package com.pet_project.backend_server.store;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavedQueryRepository extends MongoRepository<SavedQuery, String> {
}
