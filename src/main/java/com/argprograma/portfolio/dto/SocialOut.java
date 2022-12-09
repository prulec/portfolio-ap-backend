package com.argprograma.portfolio.dto;

import com.argprograma.portfolio.model.Social;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SocialOut {
    private Long id;
    private int itemOrder;
    private String url;
    private SocialTypeData socialTypeData;
    private PortfolioData portfolioData;
    
    public SocialOut () {}
    
    public SocialOut (Social social){
        id = social.getId();
        itemOrder = social.getItemOrder();
        url = social.getUrl();
        socialTypeData = new SocialTypeData(social.getSocialType());
        portfolioData = new PortfolioData(social.getPortfolio());
    }
}
