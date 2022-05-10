package com.argprograma.portfolio.dto;

import com.argprograma.portfolio.model.Education;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EducationOut {
    private Long id;
    private int itemOrder;
    private String logoUrl;
    private String institution;
    private String educationTime;
    private String title;
    private PortfolioData portfolioData;
    
    public EducationOut (Education education) {
        id = education.getId();
        itemOrder = education.getItemOrder();
        logoUrl = education.getLogoUrl();
        institution = education.getInstitution();
        educationTime = education.getEducationTime();
        title = education.getTitle();
        portfolioData = new PortfolioData(education.getPortfolio());
    }
}
