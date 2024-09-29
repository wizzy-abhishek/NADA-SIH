package com.ai.aiml10.repo;

import com.ai.aiml10.entity.UrineTestEntity;
import com.ai.aiml10.enums.Status;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UrineTestRepo extends MongoRepository<UrineTestEntity , String> {

    List<UrineTestEntity> findByAthleteId(String athleteId) ;

    List<UrineTestEntity> findByCondition(Status status);

}
