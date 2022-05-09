package com.argprograma.portfolio.service;

import com.argprograma.portfolio.model.Project;

public interface IProjectService {
    public Project createProject (Project project);
    public Project findProjectById (Long id);
    public Project updateProject (Project project);
    public void deleteProject (Project project);
}
