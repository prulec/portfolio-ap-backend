package com.argprograma.portfolio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Portfolio {
    
    @Id
    @GeneratedValue
    private long id;
    
    @Column (unique=true, length=45)
    private String name;
    
    @Column (nullable=false)
    private Boolean visible;
    
    @Column (length=255)
    private String bannerUrl;
    
    @Column (length=255)
    private String photoUrl;
    
    @Column (length=150)
    private String jobTitle;
    
    @Column (length=800)
    private String pStatement;
    
}
