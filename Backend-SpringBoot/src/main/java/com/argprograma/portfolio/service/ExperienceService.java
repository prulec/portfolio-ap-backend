package com.argprograma.portfolio.service;

import com.argprograma.portfolio.repository.ExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExperienceService implements IExperienceService {
    
    @Autowired
    private ExperienceRepository experienceRepo;
    
}
