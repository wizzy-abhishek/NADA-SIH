package com.ai.aiml10.repo;

import com.ai.aiml10.entity.UrineTestEntity;
import com.ai.aiml10.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UrineTestRepo extends JpaRepository<UrineTestEntity , String> {

    List<UrineTestEntity> findByAthletesID(String athleteId) ;

    List<UrineTestEntity> findByCondition(Status status);

}
