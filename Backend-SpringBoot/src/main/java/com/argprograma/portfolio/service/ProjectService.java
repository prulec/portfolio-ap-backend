package com.argprograma.portfolio.service;

import com.argprograma.portfolio.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService implements IProjectService {
    
    @Autowired
    private ProjectRepository projectRepo;
    
}
