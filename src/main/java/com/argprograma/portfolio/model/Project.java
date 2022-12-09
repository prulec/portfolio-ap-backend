package com.argprograma.portfolio.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Project {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @Column (nullable=false)
    private int itemOrder;
    
    @Column (length=150)
    private String name;
    
    @Column (length=100)
    private String projectTime;
    
    @Column (length=255)
    private String link;
    
    @Column (length=500)
    private String description;
    
    @JsonIgnoreProperties("portfolioSet")
    @ManyToOne (optional=false)
    private Portfolio portfolio;
    
    @OneToMany (mappedBy = "project",
                cascade = CascadeType.ALL,
                orphanRemoval = true,
                fetch = FetchType.LAZY)
    private Set<ProjectImage> projectImageSet = new HashSet<>();
    
}
