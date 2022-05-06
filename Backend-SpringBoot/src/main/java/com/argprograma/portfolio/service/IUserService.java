package com.argprograma.portfolio.service;

import com.argprograma.portfolio.model.User;
import java.util.List;

public interface IUserService {
    public User createUser (User user);
    public List<User> getUsers ();
    public User findUserByUsername (String username);
    public User findUserById (Long id);
    public User updateUser (User user);
    public boolean deleteUserByUsername (String username);
}
