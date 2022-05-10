package com.argprograma.portfolio.dto;

import com.argprograma.portfolio.model.ProjectImage;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProjectImageOut {
    
    private Long id;
    private int itemOrder;
    private String title;
    private String imageUrl;
    private ProjectData projectData;
    
    public ProjectImageOut (ProjectImage projectImage) {
        id = projectImage.getId();
        itemOrder = projectImage.getItemOrder();
        title = projectImage.getTitle();
        imageUrl = projectImage.getImageUrl();
        projectData = new ProjectData(projectImage.getProject());
    }
    
}
