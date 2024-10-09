package com.ai.aiml10.repo;

import com.ai.aiml10.entity.AthleteEntity;
import com.ai.aiml10.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AthleteRepo extends JpaRepository<AthleteEntity , String> {


    List<AthleteEntity> findBySuspicion(Status status);
}
