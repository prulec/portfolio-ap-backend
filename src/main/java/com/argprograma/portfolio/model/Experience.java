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
public class Experience {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @Column (nullable=false)
    private int itemOrder;
    
    @Column (length=255)
    private String logoUrl;
    
    @Column (length=120)
    private String enterprise;
    
    @Column (length=100)
    private String experienceTime;
    
    @Column (length=100)
    private String position;
    
    @Column (length=255)
    private String tasks;
    
    @JsonIgnoreProperties("experienciaSet")
    @ManyToOne (optional=false)
    private Portfolio portfolio;
    
}
