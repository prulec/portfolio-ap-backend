package com.argprograma.portfolio.repository;

import com.argprograma.portfolio.model.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationRepository extends JpaRepository <Education, Long> {
    
}
