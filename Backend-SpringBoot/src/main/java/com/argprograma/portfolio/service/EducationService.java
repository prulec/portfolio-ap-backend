package com.argprograma.portfolio.service;

import com.argprograma.portfolio.model.Education;
import com.argprograma.portfolio.repository.EducationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EducationService implements IEducationService {
    
    @Autowired
    private EducationRepository educationRepo;
    @Autowired
    private PortfolioService portfolioService;

    @Override
    public Education createEducation(Education education) {
        Education savedEducation = educationRepo.save(education);
        savedEducation.getPortfolio().getEducationSet().add(savedEducation);
        portfolioService.updatePortfolio(savedEducation.getPortfolio());
        return savedEducation;
    }

    @Override
    public void deleteEducation(Education education) {
        education.getPortfolio().getEducationSet().remove(education);
        portfolioService.updatePortfolio(education.getPortfolio());
        educationRepo.delete(education);
    }
    
}
