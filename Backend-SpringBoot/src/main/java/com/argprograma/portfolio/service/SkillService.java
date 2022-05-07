package com.argprograma.portfolio.service;

import com.argprograma.portfolio.model.Skill;
import com.argprograma.portfolio.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkillService implements ISkillService {
    
    @Autowired
    private SkillRepository skillRepo;
    @Autowired
    private PortfolioService portfolioService;

    @Override
    public void deleteSkill(Skill skill) {
        skill.getPortfolio().getSkillSet().remove(skill);
        portfolioService.updatePortfolio(skill.getPortfolio());
        skillRepo.delete(skill);
    }
    
}
