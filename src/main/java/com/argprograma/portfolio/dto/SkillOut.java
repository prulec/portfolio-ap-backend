package com.argprograma.portfolio.dto;

import com.argprograma.portfolio.model.Skill;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SkillOut {
    private Long id;
    private int itemOrder;
    private String name;
    private Integer skillLevel;
    private String levelTag;
    private PortfolioData portfolioData;
    
    public SkillOut (Skill skill) {
        id = skill.getId();
        itemOrder = skill.getItemOrder();
        name = skill.getName();
        skillLevel = skill.getSkillLevel();
        levelTag = skill.getLevelTag();
        portfolioData = new PortfolioData(skill.getPortfolio());
    }
}
