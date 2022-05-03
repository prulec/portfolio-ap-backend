package com.argprograma.portfolio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class User {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @Column(unique=true, length=45)
    private String username;
    
    @Column(nullable=false, length=45)
    private String password;
    
    @Column(nullable=false, length=80)
    private String firstName;
    
    @Column(nullable=false, length=80)
    private String lastName;
    
    @Column(nullable=false, length=100)
    private String email;
    
}
