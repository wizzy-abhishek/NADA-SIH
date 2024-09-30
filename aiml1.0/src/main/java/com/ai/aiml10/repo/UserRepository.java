package com.ai.aiml10.repo;

import com.ai.aiml10.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<UserEntity , String> {

    Optional<UserEntity> findByEmailIgnoreCase(String email);

}
