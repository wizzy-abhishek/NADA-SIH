package com.ai.aiml10.repo;

import com.ai.aiml10.entity.BloodTestEntity;
import com.ai.aiml10.enums.Status;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BloodTestRepo extends MongoRepository<BloodTestEntity , String> {

    List<BloodTestEntity> findByAthleteId(String athleteId) ;

    List<BloodTestEntity> findByCondition(Status status);
}
