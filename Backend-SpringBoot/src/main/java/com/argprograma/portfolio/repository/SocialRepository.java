package com.argprograma.portfolio.repository;

import com.argprograma.portfolio.model.Social;
import com.argprograma.portfolio.model.SocialType;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialRepository extends JpaRepository <Social, Long> {
    List<Social> findAllBySocialType (SocialType socialType);
}
