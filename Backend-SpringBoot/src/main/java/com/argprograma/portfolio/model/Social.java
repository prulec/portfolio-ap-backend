package com.argprograma.portfolio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Social {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @Column (nullable=false)
    private int order;
    
    @Column (length=255)
    private String url;
    
}
