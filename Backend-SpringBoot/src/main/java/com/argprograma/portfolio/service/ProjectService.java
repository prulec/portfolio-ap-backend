package com.argprograma.portfolio.service;

import com.argprograma.portfolio.model.Project;
import com.argprograma.portfolio.model.ProjectImage;
import com.argprograma.portfolio.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService implements IProjectService {
    
    @Autowired
    private ProjectRepository projectRepo;
    @Autowired
    private ProjectImageService projectImageService;
    @Autowired
    private PortfolioService portfolioService;

    @Override
    public Project createProject(Project project) {
        Project savedProject = projectRepo.save(project);
        savedProject.getPortfolio().getProjectSet().add(savedProject);
        portfolioService.updatePortfolio(savedProject.getPortfolio());
        return savedProject;
    }

    @Override
    public Project findProjectById(Long id) {
        return projectRepo.findById(id).orElse(null);
    }

    @Override
    public Project updateProject(Project project) {
        return projectRepo.save(project);
    }

    @Override
    public Project changeOrderProject(Project project, int newOrder) {
        int currentOrder = project.getItemOrder();
        if (newOrder < currentOrder) {
            for (Project item : project.getPortfolio().getProjectSet()) {
                if (item.getItemOrder()>=newOrder && item.getItemOrder()<currentOrder) {
                    item.setItemOrder(item.getItemOrder()+1);
                }
            }
        } else if (newOrder > currentOrder) {
            for (Project item : project.getPortfolio().getProjectSet()) {
                if (item.getItemOrder()<=newOrder && item.getItemOrder()>currentOrder) {
                    item.setItemOrder(item.getItemOrder()-1);
                }
            }
        }
        project.setItemOrder(newOrder);
        portfolioService.updatePortfolio(project.getPortfolio());
        return project;
    }

    @Override
    public void deleteProject(Project project) {
        for (ProjectImage projectImage : project.getProjectImageSet()) {
            projectImageService.deleteProjectImage(projectImage);
        }
        project.getPortfolio().getProjectSet().remove(project);
        portfolioService.updatePortfolio(project.getPortfolio());
    }
    
}
