package com.argprograma.portfolio.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
    private String ProjectTime;
    
    @Column (length=255)
    private String link;
    
    @Column (length=500)
    private String description;
    
    @ManyToOne (optional=false)
    private Portfolio portfolio;
    
    @OneToMany (mappedBy="project", fetch = FetchType.LAZY)
    private Set<ProjectImage> projectImageSet = new HashSet<>();
    
}
