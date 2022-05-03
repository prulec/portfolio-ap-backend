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
    
    @ManyToOne
    @JoinColumn (name = "user_id",
		foreignKey = @ForeignKey
    )
    private User user;
    
    @OneToMany (mappedBy="portfolio", fetch = FetchType.LAZY)
    private Set<Social> socialSet = new HashSet<>();
    
    @OneToMany (mappedBy="portfolio", fetch = FetchType.LAZY)
    private Set<Experience> experienceSet = new HashSet<>();
    
    @OneToMany (mappedBy="portfolio", fetch = FetchType.LAZY)
    private Set<Education> educationSet = new HashSet<>();
    
    @OneToMany (mappedBy="portfolio", fetch = FetchType.LAZY)
    private Set<Skill> skillSet = new HashSet<>();
    
    @OneToMany (mappedBy="portfolio", fetch = FetchType.LAZY)
    private Set<Project> projectSet = new HashSet<>();
    
}
