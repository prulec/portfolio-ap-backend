package com.argprograma.portfolio.service;

import com.argprograma.portfolio.repository.SocialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SocialService implements ISocialService {
    
    @Autowired
    private SocialRepository socialRepo;
    
}
