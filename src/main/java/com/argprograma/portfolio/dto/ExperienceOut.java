package com.argprograma.portfolio.dto;

import com.argprograma.portfolio.model.Experience;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ExperienceOut {
    private Long id;
    private int itemOrder;
    private String logoUrl;
    private String enterprise;
    private String experienceTime;
    private String position;
    private String tasks;
    private PortfolioData portfolioData;
    
    public ExperienceOut (Experience experience) {
        id = experience.getId();
        itemOrder = experience.getItemOrder();
        logoUrl = experience.getLogoUrl();
        enterprise = experience.getEnterprise();
        experienceTime = experience.getExperienceTime();
        position = experience.getPosition();
        tasks = experience.getTasks();
        portfolioData = new PortfolioData(experience.getPortfolio());
    }
}
