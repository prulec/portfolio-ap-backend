package com.argprograma.portfolio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Project {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @Column (nullable=false)
    private int order;
    
    @Column (length=150)
    private String name;
    
    @Column (length=100)
    private String time;
    
    @Column (length=255)
    private String link;
    
    @Column (length=500)
    private String description;
    
}
