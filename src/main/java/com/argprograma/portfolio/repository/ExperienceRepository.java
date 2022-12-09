package com.argprograma.portfolio.repository;

import com.argprograma.portfolio.model.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperienceRepository extends JpaRepository <Experience, Long> {
    
}
