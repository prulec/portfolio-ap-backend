package com.argprograma.portfolio.dto;

import com.argprograma.portfolio.model.SocialType;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SocialTypeData {
    
    private Long id;
    private String name;
    private String iconUrl;
    
    public SocialTypeData () {};
    
    public SocialTypeData (SocialType socialType) {
        id = socialType.getId();
        name = socialType.getName();
        iconUrl = socialType.getIconUrl();
    }
    
}
