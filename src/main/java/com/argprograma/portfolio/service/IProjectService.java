package com.argprograma.portfolio.service;

import com.argprograma.portfolio.model.Project;
import com.argprograma.portfolio.model.ProjectImage;

public interface IProjectService {
    public Project createProject (Project project);
    public Project findProjectById (Long id);
    public Project updateProject (Project project);
    public Project changeOrderProject (Project project, int newOrder);
    public Project disconnectProjectImage (ProjectImage projectImage);
}
