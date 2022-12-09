package com.argprograma.portfolio.security.service;

import com.argprograma.portfolio.model.User;
import com.argprograma.portfolio.security.entity.PrincipalUser;
import com.argprograma.portfolio.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IUserService userService;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findUserByUsername(username);
        if (user != null) {
            return PrincipalUser.build(user);
        } else return null;
    }
    
}
