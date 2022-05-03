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
public class Social {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @Column (nullable=false)
    private int itemOrder;
    
    @Column (length=255)
    private String url;
    
    @ManyToOne
    @JoinColumn (name = "social_type_id",
		foreignKey = @ForeignKey
    )
    private SocialType socialType;
    
    @ManyToOne
    @JoinColumn (name = "portfolio_id",
		foreignKey = @ForeignKey
    )
    private Portfolio portfolio;
    
}
