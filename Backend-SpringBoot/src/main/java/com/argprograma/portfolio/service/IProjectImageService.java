package com.argprograma.portfolio.service;

import com.argprograma.portfolio.model.ProjectImage;

public interface IProjectImageService {
    public ProjectImage createProjectImage (ProjectImage projectImage);
    public void deleteProjectImage (ProjectImage projectImage);
}
