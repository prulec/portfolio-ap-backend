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
    public Experience findExperienceById(Long id) {
        return experienceRepo.findById(id).orElse(null);
    }

    @Override
    public Experience updateExperience(Experience experience) {
        return experienceRepo.save(experience);
    }

    @Override
    public Experience changeOrderExperience(Experience experience, int newOrder) {
        int currentOrder = experience.getItemOrder();
        if (newOrder < currentOrder) {
            for (Experience item : experience.getPortfolio().getExperienceSet()) {
                if (item.getItemOrder()>=newOrder && item.getItemOrder()<currentOrder) {
                    item.setItemOrder(item.getItemOrder()+1);
                }
            }
        } else if (newOrder > currentOrder) {
            for (Experience item : experience.getPortfolio().getExperienceSet()) {
                if (item.getItemOrder()<=newOrder && item.getItemOrder()>currentOrder) {
                    item.setItemOrder(item.getItemOrder()-1);
                }
            }
        }
        experience.setItemOrder(newOrder);
        portfolioService.updatePortfolio(experience.getPortfolio());
        return experience;
    }

    @Override
    public void deleteExperience(Experience experience) {
        experience.getPortfolio().getExperienceSet().remove(experience);
        portfolioService.updatePortfolio(experience.getPortfolio());
    }
    
}
