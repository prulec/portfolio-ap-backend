package com.argprograma.portfolio.dto;

import com.argprograma.portfolio.model.Project;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProjectData {
    
    private Long id;
    private String name;
    private String projectTime;
    private String link;
    private String description;
    private String username;
    private String portfolioName;
    
    public ProjectData () {}
    
    public ProjectData (Project project) {
        id = project.getId();
        name = project.getName();
        projectTime = project.getProjectTime();
        link = project.getLink();
        description = project.getDescription();
        username = project.getPortfolio().getUser().getUsername();
    }
    
}
