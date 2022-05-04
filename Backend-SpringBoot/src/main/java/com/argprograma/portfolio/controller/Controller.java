package com.argprograma.portfolio.controller;

import com.argprograma.portfolio.dto.PortfolioData;
import com.argprograma.portfolio.dto.UserData;
import com.argprograma.portfolio.model.Portfolio;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    
    /* ADMIN */
    
    /* User */
    
    @PostMapping ("adduser")
    public String addUser (@RequestBody UserData data) {
        return "User added";
    }
    
    @GetMapping ("listusers")
    public List<String> getUsers () {
        return null;
    }
    
    @GetMapping ("user/{username}")
    public UserData getUserByUsername (@PathVariable String username) {
        return null;
    }
    
    @PatchMapping ("updateuser")
    public String updateUser (@RequestBody UserData data) {
        return "User updated";
    }
    
    @DeleteMapping ("user/{username}/delete")
    public String deleteUser (@PathVariable String username) {
        return "User deleted";
    }
    
    /* Portfolio */
    
    @PostMapping ("{username}/addportfolio")
    public String addPortfolio (@PathVariable String username,
                                @RequestBody PortfolioData data) {
        return "Portfolio added";
    }
    
    @GetMapping ("{username}/listportfolios")
    public List<PortfolioData> getPortfoliosByUsername (@PathVariable String username) {
        return null;
    }
    
    @PatchMapping ("updateportfolioname")
    public String updatePortfolioName (@RequestBody PortfolioData data) {
        return "Portfolio's name updated";
    }
    
    @DeleteMapping ("{portfolio_id}/delete")
    public String deletePortfolio (@PathVariable Long portfolio_id) {
        return "Portfolio deleted";
    }
    
    
    /* EDIT MODE */
    
    @GetMapping ("{portfolio_id}")
    public Portfolio getPortfolio (@PathVariable Long portfolio_id){
        return null;
    }
    
    @PatchMapping ("{portfolio_id}/visibility")
    public String toggleVisibility (@PathVariable Long portfolio_id){
        return "Visibility on/off";
    }
    
    @DeleteMapping ("{portfolio_id}/{section}/{id}/delete")
    public String deleteItem (@PathVariable Long portfolio_id,
                              @PathVariable String section,
                              @PathVariable Long id) {
        return "Item deleted";
    }
    
    @PatchMapping ("{portfolio_id}/{section}/update")
    public String updateItem (@PathVariable Long portfolio_id,
                              @PathVariable String section) {
        return "Item updated";
    }
    
    @PostMapping ("{portfolio_id}/{section}/add")
    public String addItem (@PathVariable Long portfolio_id,
                           @PathVariable String section) {
        return "item created";
    }
    
}
