package com.argprograma.portfolio.controller;

import com.argprograma.portfolio.dto.PortfolioData;
import com.argprograma.portfolio.dto.UserData;
import com.argprograma.portfolio.model.Portfolio;
import com.argprograma.portfolio.model.SocialType;
import com.argprograma.portfolio.model.User;
import com.argprograma.portfolio.service.IUserService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
    
    @Autowired
    private IUserService userService;
    
    /* User */
    
    @PostMapping ("adduser")
    public User addUser (@RequestBody UserData data) {
        // Agrega usuario, pasando 'null' en 'id'
        User user = new User();
        user.setId(null);
        user.setUsername(data.getUsername());
        user.setPassword(data.getPassword());
        user.setFirstName(data.getFirstName());
        user.setLastName(data.getLastName());
        user.setEmail(data.getEmail());        
        return userService.createUser(user);
    }
    
    @GetMapping ("listusers")
    public List<String> getUsers () {
        // Lista con los username existentes
        List<String> usernames = new ArrayList<> ();
        List<User> users = userService.getUsers();
        for (int i = 0; i < users.size(); i++) {
            usernames.add(users.get(i).getUsername());
        }
        return usernames;
    }
    
    @GetMapping ("user/{username}")
    public UserData getUser (@PathVariable String username) {
        // Muestra datos de usuario SIN la lista de portfolios
        UserData userData = new UserData();
        userData.setId(userService.findUserByUsername(username).getId());
        userData.setUsername(userService.findUserByUsername(username).getUsername());
        userData.setPassword(userService.findUserByUsername(username).getPassword());
        userData.setFirstName(userService.findUserByUsername(username).getFirstName());
        userData.setLastName(userService.findUserByUsername(username).getLastName());
        userData.setEmail(userService.findUserByUsername(username).getEmail());
        return userData;
    }
    
    @PatchMapping ("updateuser")
    public User updateUser (@RequestBody UserData data) {
        // Actualiza datos de usuario que corresponda con id recibida
        User user = userService.findUserById(data.getId());
        if (!data.getUsername().isEmpty()) user.setUsername(data.getUsername());
        if (!data.getPassword().isEmpty()) user.setPassword(data.getPassword());
        if (!data.getFirstName().isEmpty()) user.setFirstName(data.getFirstName());
        if (!data.getLastName().isEmpty()) user.setLastName(data.getLastName());
        if (!data.getEmail().isEmpty()) user.setEmail(data.getEmail());
        return userService.updateUser(user);
    }
    
    @DeleteMapping ("deleteuser/{username}")
    public Boolean deleteUser (@PathVariable String username) {
        // Borra usuario y todos los portfolios que le pertenecen
        User user = userService.findUserByUsername(username);
        for (Portfolio portfolio : user.getPortfolioSet()) {
            if (!deletePortfolio(portfolio.getId())) return false;
            System.out.println("Portfolio " + portfolio.getName() + " eliminado!");
        }
        return userService.deleteUserByUsername(username);
    }
    
    
    /* Portfolio */
    
    @PostMapping ("addportfolio/{username}")
    public String addPortfolio (@PathVariable String username,
                                @RequestBody PortfolioData data) {
        // Agrega portfolio recibiendo id, name,
        // y asignando: visible=false, user buscando por username 
        return "Portfolio added";
    }
    
    @GetMapping ("listportfolios/{username}")
    public List<PortfolioData> getPortfoliosUser
        (@PathVariable String username) {
        // Devuelve lista de portfolios del usuario (sólo id y name)
        return null;
    }
    
    @PatchMapping ("updateportfolioname")
    public String updatePortfolioName (@RequestBody PortfolioData data) {
        // Actualiza name del portfolio que corresponde al id recibido
        return "Portfolio's name updated";
    }
    
    @DeleteMapping ("deleteportfolio/{portfolio_id}")
    public Boolean deletePortfolio (@PathVariable Long portfolio_id) {
        // Elimina portfolio que corresponde al id recibido
        // actualizando lista del usuario correspondiente y
        // eliminando los items que referencian al portfolio
        return true;
    }
    
    
    /* Portfolio --> SocialType */
    
    @PostMapping ("addsocialtype")
    public String addSocialType (@RequestBody SocialType data) {
        // Agrega un tipo de red social, asignando null a id
        return "Social type added";
    }
    
    @PutMapping ("updatesocialtype")
    public String updateSocialType (@RequestBody SocialType data) {
        // Actualiza SocialType que corresponde al id recibido
        return "Social type updated";
    }
    
    @DeleteMapping ("deletesocialtype/{socialtype_id}")
    public String deleteSocialType (@PathVariable Long socialtype_id) {
        // Elimina SocialType correspondiente al id de la ruta
        // y todos los elementos Social que lo referencien
        return "Social type deleted";
    }
    
    @GetMapping ("socialtypes")
    public List<SocialType> getSocialTypes () {
        // Devuelve todos los tipos de redes sociales registradas
        return null;
    }
    
}
