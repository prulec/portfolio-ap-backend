package com.argprograma.portfolio.dto;

import com.argprograma.portfolio.model.Portfolio;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PortfolioData {
    
    private Long id;
    private String name;
    private String username;
    
    public PortfolioData () {}
    
    public PortfolioData (Portfolio portfolio) {
        id = portfolio.getId();
        name = portfolio.getName();
        username = portfolio.getUser().getUsername();
    }
    
}
