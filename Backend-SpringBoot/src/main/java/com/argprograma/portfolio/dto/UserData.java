package com.argprograma.portfolio.dto;

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
    
}
