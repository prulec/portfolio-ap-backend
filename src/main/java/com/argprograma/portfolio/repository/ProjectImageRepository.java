package com.argprograma.portfolio.repository;

import com.argprograma.portfolio.model.ProjectImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectImageRepository extends JpaRepository <ProjectImage, Long> {
    
}
