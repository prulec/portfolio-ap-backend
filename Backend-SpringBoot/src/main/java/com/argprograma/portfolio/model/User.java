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
    @Column(unique=true)
    private String username;
    @Column(nullable=false)
    private String password;
    @Column(nullable=false)
    private String firstName;
    @Column(nullable=false)
    private String lastName;
    @Column(nullable=false)
    private String email;
    
}
