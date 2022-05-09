package com.argprograma.portfolio.service;

import com.argprograma.portfolio.model.Experience;
import com.argprograma.portfolio.repository.ExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExperienceService implements IExperienceService {
    
    @Autowired
    private ExperienceRepository experienceRepo;
    @Autowired
    private PortfolioService portfolioService;

    @Override
    public Experience createExperience(Experience experience) {
        Experience savedExperience = experienceRepo.save(experience);
        savedExperience.getPortfolio().getExperienceSet().add(savedExperience);
        portfolioService.updatePortfolio(savedExperience.getPortfolio());
        return savedExperience;
    }

    @Override
    public void deleteExperience(Experience experience) {
        experience.getPortfolio().getExperienceSet().remove(experience);
        portfolioService.updatePortfolio(experience.getPortfolio());
        experienceRepo.delete(experience);
    }
    
}
