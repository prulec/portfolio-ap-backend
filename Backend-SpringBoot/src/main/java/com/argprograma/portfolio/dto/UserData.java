package com.argprograma.portfolio.dto;

import com.argprograma.portfolio.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserData {
    
    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    
    public UserData () {}
    
    public UserData (User user) {
        id = user.getId();
        username = user.getUsername();
        //password = user.getPassword();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        email = user.getEmail();
    }
    
}
