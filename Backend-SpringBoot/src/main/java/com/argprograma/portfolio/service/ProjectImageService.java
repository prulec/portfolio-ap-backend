package com.argprograma.portfolio.service;

import com.argprograma.portfolio.repository.ProjectImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectImageService implements IProjectImageService {
    
    @Autowired
    private ProjectImageRepository projectImageRepo;
    
}
