package com.argprograma.portfolio.dto;

import com.argprograma.portfolio.model.Education;
import com.argprograma.portfolio.model.Experience;
import com.argprograma.portfolio.model.Portfolio;
import com.argprograma.portfolio.model.Project;
import com.argprograma.portfolio.model.Skill;
import com.argprograma.portfolio.model.Social;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PortfolioOut {
    private Long id;
    private String name;
    private boolean visible;
    private String bannerUrl;
    private String photoUrl;
    private String jobTitle;
    private String pStatement;
    private UserData user;
    private List<SocialOut> socialList = new ArrayList<>();
    private List<ExperienceOut> experienceList = new ArrayList<>();
    private List<EducationOut> educationList = new ArrayList<>();
    private List<SkillOut> skillList = new ArrayList<>();
    private List<ProjectOut> projectList = new ArrayList<>();
    
    public PortfolioOut () {}
    
    public PortfolioOut (Portfolio portfolio) {
        
        id = portfolio.getId();
        name = portfolio.getName();
        visible = portfolio.isVisible();
        bannerUrl = portfolio.getBannerUrl();
        photoUrl = portfolio.getPhotoUrl();
        jobTitle = portfolio.getJobTitle();
        pStatement = portfolio.getPStatement();
        
        user = new UserData(portfolio.getUser());
        
        for (Social social : portfolio.getSocialSet()) {
            socialList.add(new SocialOut(social));
        }
        socialList.sort((p1,p2) -> p1.getItemOrder()-p2.getItemOrder());
        
        for (Experience experience : portfolio.getExperienceSet()) {
            experienceList.add(new ExperienceOut(experience));
        }
        experienceList.sort((p1,p2) -> p1.getItemOrder()-p2.getItemOrder());
        
        for (Education education : portfolio.getEducationSet()) {
            educationList.add(new EducationOut(education));
        }
        educationList.sort((p1,p2) -> p1.getItemOrder()-p2.getItemOrder());
        
        for (Skill skill : portfolio.getSkillSet()) {
            skillList.add(new SkillOut(skill));
        }
        skillList.sort((p1,p2) -> p1.getItemOrder()-p2.getItemOrder());
        
        for (Project project : portfolio.getProjectSet()) {
            projectList.add(new ProjectOut(project));
        }
        projectList.sort((p1,p2) -> p1.getItemOrder()-p2.getItemOrder());
        
    }
}
