package com.argprograma.portfolio.service;

import com.argprograma.portfolio.model.ProjectImage;

public interface IProjectImageService {
    public ProjectImage createProjectImage (ProjectImage projectImage);
    public ProjectImage findProjectImageById (Long id);
    public ProjectImage updateProjectImage(ProjectImage projectImage);
    public ProjectImage changeOrderProjectImage (ProjectImage projectImage, int newOrder);
}
