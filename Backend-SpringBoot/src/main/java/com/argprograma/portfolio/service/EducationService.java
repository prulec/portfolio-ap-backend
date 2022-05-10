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
    private IPortfolioService portfolioService;

    @Override
    public Education createEducation(Education education) {
        Education savedEducation = educationRepo.save(education);
        savedEducation.getPortfolio().getEducationSet().add(savedEducation);
        portfolioService.updatePortfolio(savedEducation.getPortfolio());
        return savedEducation;
    }

    @Override
    public Education findEducationById(Long id) {
        return educationRepo.findById(id).orElse(null);
    }

    @Override
    public Education updateEducation(Education education) {
        return educationRepo.save(education);
    }

    @Override
    public Education changeOrderEducation(Education education, int newOrder) {
        int currentOrder = education.getItemOrder();
        if (newOrder < currentOrder) {
            for (Education item : education.getPortfolio().getEducationSet()) {
                if (item.getItemOrder()>=newOrder && item.getItemOrder()<currentOrder) {
                    item.setItemOrder(item.getItemOrder()+1);
                }
            }
        } else if (newOrder > currentOrder) {
            for (Education item : education.getPortfolio().getEducationSet()) {
                if (item.getItemOrder()<=newOrder && item.getItemOrder()>currentOrder) {
                    item.setItemOrder(item.getItemOrder()-1);
                }
            }
        }
        education.setItemOrder(newOrder);
        portfolioService.updatePortfolio(education.getPortfolio());
        return education;
    }

    @Override
    public void deleteEducation(Education education) {
        education.getPortfolio().getEducationSet().remove(education);
        portfolioService.updatePortfolio(education.getPortfolio());
    }
    
}
