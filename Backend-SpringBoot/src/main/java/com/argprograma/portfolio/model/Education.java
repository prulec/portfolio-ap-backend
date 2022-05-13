package com.argprograma.portfolio.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Education {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @Column (nullable=false)
    private int itemOrder;
    
    @Column (length=255)
    private String logoUrl;
    
    @Column (length=120)
    private String institution;
    
    @Column (length=100)
    private String educationTime;
    
    @Column (length=100)
    private String title;
    
    @JsonIgnoreProperties("educationSet")
    @ManyToOne (optional=false)
    private Portfolio portfolio;
    
}
