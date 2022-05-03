package com.argprograma.portfolio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
    
    @ManyToOne
    @JoinColumn (name = "project_id",
		foreignKey = @ForeignKey
    )
    private Project project;
    
}
