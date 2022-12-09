package com.argprograma.portfolio.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class SocialType {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @Column (unique=true, length=45)
    private String name;
    
    @Column (length=255)
    private String iconUrl;
    
    @OneToMany (mappedBy = "socialType",
                cascade = CascadeType.ALL,
                orphanRemoval = true,
                fetch = FetchType.LAZY)
    private Set<Social> socialSet = new HashSet<>();
    
}
