package com.argprograma.portfolio.service;

import com.argprograma.portfolio.model.ProjectImage;
import com.argprograma.portfolio.repository.ProjectImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectImageService implements IProjectImageService {
    
    @Autowired
    private ProjectImageRepository projectImageRepo;
    @Autowired
    private ProjectService projectService;

    @Override
    public void deleteProjectImage(ProjectImage projectImage) {
        projectImage.getProject().getProjectImageSet().remove(projectImage);
        projectService.updateProject(projectImage.getProject());
        projectImageRepo.delete(projectImage);
    }
    
}
