package com.argprograma.portfolio.dto;

import com.argprograma.portfolio.model.Portfolio;
import com.argprograma.portfolio.model.User;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserOut {
    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private List<PortfolioOut> portfolioList = new ArrayList<>();
    
    public UserOut (User user) {
        id = user.getId();
        username = user.getUsername();
        password = user.getPassword();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        email = user.getEmail();
        for (Portfolio portfolio : user.getPortfolioSet()) {
            portfolioList.add(new PortfolioOut(portfolio));
        }
    }
}
