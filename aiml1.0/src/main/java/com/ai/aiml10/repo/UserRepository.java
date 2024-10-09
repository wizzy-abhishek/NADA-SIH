package com.ai.aiml10.repo;

import com.ai.aiml10.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity , Long> {

    Optional<UserEntity> findByEmailIgnoreCase(String email);

}
