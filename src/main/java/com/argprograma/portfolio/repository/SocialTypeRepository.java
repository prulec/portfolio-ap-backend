package com.argprograma.portfolio.repository;

import com.argprograma.portfolio.model.SocialType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialTypeRepository extends JpaRepository <SocialType, Long> {
    SocialType findByName (String name);
}
