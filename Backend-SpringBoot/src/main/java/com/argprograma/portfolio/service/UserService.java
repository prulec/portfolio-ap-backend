package com.argprograma.portfolio.service;

import com.argprograma.portfolio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    
    @Autowired
    private UserRepository userRepo;
    
}
