package com.ai.aiml10.repo;

import com.ai.aiml10.entity.BiologicalPassportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BiologicalPassportRepo extends JpaRepository<BiologicalPassportEntity , String > {

    BiologicalPassportEntity findByAthletesID(String athleteId);

}
