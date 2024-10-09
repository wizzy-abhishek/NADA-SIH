package com.ai.aiml10.repo;

import com.ai.aiml10.entity.BloodTestEntity;
import com.ai.aiml10.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BloodTestRepo extends JpaRepository<BloodTestEntity , String> {

    List<BloodTestEntity> findByAthletesID(String athleteId) ;

    List<BloodTestEntity> findByCondition(Status status);
}
