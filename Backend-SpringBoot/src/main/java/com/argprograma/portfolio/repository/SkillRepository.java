package com.argprograma.portfolio.repository;

import com.argprograma.portfolio.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends JpaRepository <Skill, Long> {
    
}
