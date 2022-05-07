package com.argprograma.portfolio.service;

import com.argprograma.portfolio.model.Education;
import com.argprograma.portfolio.model.Experience;
import com.argprograma.portfolio.model.Portfolio;
import com.argprograma.portfolio.model.Project;
import com.argprograma.portfolio.model.Skill;
import com.argprograma.portfolio.model.Social;
import com.argprograma.portfolio.model.User;
import com.argprograma.portfolio.repository.PortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PortfolioService implements IPortfolioService {
    
    @Autowired
    private PortfolioRepository portfolioRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private SocialService socialService;
    @Autowired
    private ExperienceService experienceService;
    @Autowired
    private EducationService educationService;
    @Autowired
    private SkillService skillService;
    @Autowired
    private ProjectService projectService;

    @Override
    public Portfolio createPortfolio(Portfolio portfolio) {
        Portfolio savedPortfolio = portfolioRepo.save(portfolio);
        User user = savedPortfolio.getUser();
        user.getPortfolioSet().add(savedPortfolio);
        userService.updateUser(user);
        return savedPortfolio;
    }

    @Override
    public Portfolio findPortfolioById(Long id) {
        return portfolioRepo.findById(id).orElse(null);
    }

    @Override
    public Portfolio updatePortfolio(Portfolio portfolio) {
        return portfolioRepo.save(portfolio);
    }

    @Override
    public void deletePortfolio(Portfolio portfolio) {
        for (Social social : portfolio.getSocialSet()) {
            socialService.deleteSocial(social);
        }
        for (Experience experience : portfolio.getExperienceSet()) {
            experienceService.deleteExperience(experience);
        }
        for (Education education : portfolio.getEducationSet()) {
            educationService.deleteEducation(education);
        }
        for (Skill skill : portfolio.getSkillSet()) {
            skillService.deleteSkill(skill);
        }
        for (Project project : portfolio.getProjectSet()) {
            projectService.deleteProject(project);
        }
        portfolio.getUser().getPortfolioSet().remove(portfolio);
        userService.updateUser(portfolio.getUser());
        portfolioRepo.delete(portfolio);
    }
    
}
