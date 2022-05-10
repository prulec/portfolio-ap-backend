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
    private IPortfolioService portfolioService;

    @Override
    public Skill createSkill(Skill skill) {
        Skill savedSkill = skillRepo.save(skill);
        savedSkill.getPortfolio().getSkillSet().add(savedSkill);
        portfolioService.updatePortfolio(savedSkill.getPortfolio());
        return savedSkill;
    }

    @Override
    public Skill findSkillById(Long id) {
        return skillRepo.findById(id).orElse(null);
    }

    @Override
    public Skill updateSkill(Skill skill) {
        return skillRepo.save(skill);
    }

    @Override
    public Skill changeOrderSkill(Skill skill, int newOrder) {
        int currentOrder = skill.getItemOrder();
        if (newOrder < currentOrder) {
            for (Skill item : skill.getPortfolio().getSkillSet()) {
                if (item.getItemOrder()>=newOrder && item.getItemOrder()<currentOrder) {
                    item.setItemOrder(item.getItemOrder()+1);
                }
            }
        } else if (newOrder > currentOrder) {
            for (Skill item : skill.getPortfolio().getSkillSet()) {
                if (item.getItemOrder()<=newOrder && item.getItemOrder()>currentOrder) {
                    item.setItemOrder(item.getItemOrder()-1);
                }
            }
        }
        skill.setItemOrder(newOrder);
        portfolioService.updatePortfolio(skill.getPortfolio());
        return skill;
    }
    
}
