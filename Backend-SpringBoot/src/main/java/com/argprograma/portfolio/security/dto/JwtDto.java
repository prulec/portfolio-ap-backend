package com.argprograma.portfolio.security.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class JwtDto {
    private String token;

    public JwtDto(String token) {
        this.token = token;
    }

    public JwtDto() {
    }
    
}
