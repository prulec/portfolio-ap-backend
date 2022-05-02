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
    @Column (unique=true)
    private String name;
    @Column (nullable=false)
    private Boolean visible;
    private String bannerUrl;
    private String photoUrl;
    private String jobTitle;
    private String pStatement;
    
}
