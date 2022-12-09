package com.argprograma.portfolio.service;

import com.argprograma.portfolio.model.User;
import com.argprograma.portfolio.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    
    @Autowired
    private UserRepository userRepo;

    @Override
    public User createUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public User findUserById(Long id) {
        return userRepo.findById(id).orElse(null);
    }

    @Override
    public User updateUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public void deleteUser (User user) {
        userRepo.delete(user);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepo.existsByUsername(username);
    }
    
}
