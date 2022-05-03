package com.argprograma.portfolio.service;

import com.argprograma.portfolio.repository.SocialTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SocialTypeService implements ISocialTypeService {
    
    @Autowired
    private SocialTypeRepository socialTypeRepo;
    
}
