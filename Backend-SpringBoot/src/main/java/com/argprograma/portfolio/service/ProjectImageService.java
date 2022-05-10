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
    private IProjectService projectService;

    @Override
    public ProjectImage createProjectImage(ProjectImage projectImage) {
        ProjectImage savedprojectImage = projectImageRepo.save(projectImage);
        savedprojectImage.getProject().getProjectImageSet().add(savedprojectImage);
        projectService.updateProject(savedprojectImage.getProject());
        return savedprojectImage;
    }

    @Override
    public ProjectImage findProjectImageById(Long id) {
        return projectImageRepo.findById(id).orElse(null);
    }

    @Override
    public ProjectImage updateProjectImage(ProjectImage projectImage) {
        return projectImageRepo.save(projectImage);
    }

    @Override
    public ProjectImage changeOrderProjectImage(ProjectImage projectImage, int newOrder) {
        int currentOrder = projectImage.getItemOrder();
        if (newOrder < currentOrder) {
            for (ProjectImage item : projectImage.getProject().getProjectImageSet()) {
                if (item.getItemOrder()>=newOrder && item.getItemOrder()<currentOrder) {
                    item.setItemOrder(item.getItemOrder()+1);
                }
            }
        } else if (newOrder > currentOrder) {
            for (ProjectImage item : projectImage.getProject().getProjectImageSet()) {
                if (item.getItemOrder()<=newOrder && item.getItemOrder()>currentOrder) {
                    item.setItemOrder(item.getItemOrder()-1);
                }
            }
        }
        projectImage.setItemOrder(newOrder);
        projectService.updateProject(projectImage.getProject());
        return projectImage;
    }

    @Override
    public void deleteProjectImage(ProjectImage projectImage) {
        projectImage.getProject().getProjectImageSet().remove(projectImage);
        projectService.updateProject(projectImage.getProject());
    }
    
}
