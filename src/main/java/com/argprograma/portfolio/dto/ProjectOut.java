package com.argprograma.portfolio.dto;

import com.argprograma.portfolio.model.Project;
import com.argprograma.portfolio.model.ProjectImage;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProjectOut {
    private Long id;
    private int itemOrder;
    private String name;
    private String projectTime;
    private String link;
    private String description;
    private List<ProjectImageOut> projectImageList = new ArrayList<>();
    private PortfolioData portfolioData;
    
    public ProjectOut (Project project) {
        id = project.getId();
        itemOrder = project.getItemOrder();
        name = project.getName();
        projectTime = project.getProjectTime();
        link = project.getLink();
        description = project.getDescription();
        for (ProjectImage projectImage : project.getProjectImageSet()) {
            projectImageList.add(new ProjectImageOut(projectImage));
        }
        projectImageList.sort((p1,p2) -> p1.getItemOrder()-p2.getItemOrder());
        portfolioData = new PortfolioData(project.getPortfolio());
    }
}
