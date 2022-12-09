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
public class ProjectImage {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @Column (nullable=false)
    private int itemOrder;
    
    @Column (length=45)
    private String title;
    
    @Column (length=255)
    private String imageUrl;
    
    @JsonIgnoreProperties("projectImageSet")
    @ManyToOne (optional=false)
    private Project project;
    
}
