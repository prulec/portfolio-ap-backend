package com.argprograma.portfolio.util;

import com.argprograma.portfolio.model.User;
import com.argprograma.portfolio.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CreateAdmin implements CommandLineRunner {

    @Autowired
    private IUserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    private final String PASSWORD = "admin";
    
    @Override
    public void run(String... args) throws Exception {
        boolean existsAdmin = userService.existsByUsername("prulec");
        if (existsAdmin) {
            User adminUser = userService.findUserByUsername("prulec");
            adminUser.setPassword(passwordEncoder.encode(PASSWORD));
            userService.updateUser(adminUser);
        } else {
            User adminUser = new User();
            adminUser.setId(null);
            adminUser.setUsername("prulec");
            adminUser.setPassword(passwordEncoder.encode(PASSWORD));
            adminUser.setFirstName("Pablo");
            adminUser.setLastName("Rule Calvi");
            adminUser.setEmail("prulec@gmail.com");
            userService.createUser(adminUser);
        }
    }
    
}
