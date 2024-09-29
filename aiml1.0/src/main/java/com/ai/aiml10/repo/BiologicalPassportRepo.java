package com.ai.aiml10.repo;

import com.ai.aiml10.entity.BiologicalPassportEntity;
import com.ai.aiml10.enums.Status;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BiologicalPassportRepo extends MongoRepository<BiologicalPassportEntity , String > {

    BiologicalPassportEntity findByAthleteId(String athleteId);

}
