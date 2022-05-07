package com.argprograma.portfolio.controller;

import com.argprograma.portfolio.dto.PortfolioData;
import com.argprograma.portfolio.dto.UserData;
import com.argprograma.portfolio.model.Portfolio;
import com.argprograma.portfolio.model.SocialType;
import com.argprograma.portfolio.model.User;
import com.argprograma.portfolio.service.IPortfolioService;
import com.argprograma.portfolio.service.ISocialTypeService;
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
    @Autowired
    private IPortfolioService portfolioService;
    @Autowired
    private ISocialTypeService socialTypeService;
    
    /* User */
    
    @PostMapping ("adduser")
    public User createUser (@RequestBody UserData data) {
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
        User user = userService.findUserByUsername(username);
        if (user!=null) {
            userData.setId(user.getId());
            userData.setUsername(user.getUsername());
            userData.setPassword(user.getPassword());
            userData.setFirstName(user.getFirstName());
            userData.setLastName(user.getLastName());
            userData.setEmail(user.getEmail());
        } else System.out.println("El username no existe...");
        return userData;
    }
    
    @PatchMapping ("updateuser")
    public User updateUser (@RequestBody UserData data) {
        // Actualiza datos de usuario que corresponda con id recibida
        User user = userService.findUserById(data.getId());
        if (user!=null) {
            if (!data.getUsername().isEmpty()) user.setUsername(data.getUsername());
            if (!data.getPassword().isEmpty()) user.setPassword(data.getPassword());
            if (!data.getFirstName().isEmpty()) user.setFirstName(data.getFirstName());
            if (!data.getLastName().isEmpty()) user.setLastName(data.getLastName());
            if (!data.getEmail().isEmpty()) user.setEmail(data.getEmail());
            return userService.updateUser(user);
        } else System.out.println("El User no existe...");
        return user;
    }
    
    @DeleteMapping ("deleteuser/{username}")
    public void deleteUser (@PathVariable String username) {
        // Borra usuario y todos los portfolios que le pertenecen
        User user = userService.findUserByUsername(username);
        if (user!=null) {
            userService.deleteUser(user);
            System.out.println("Usuario " + user.getUsername() + " eliminado!");
        } else System.out.println("El username no existe...");
    }
    
    
    /* Portfolio */
    
    @PostMapping ("addportfolio/{username}")
    public Portfolio createPortfolio (@PathVariable String username,
                                @RequestBody PortfolioData data) {
        // Agrega portfolio recibiendo id, name,
        // y asignando: visible=false, user buscando por username
        User user = userService.findUserByUsername(username);
        Portfolio portfolio = new Portfolio();
        if (user!=null) {
            portfolio.setId(null);
            portfolio.setName(data.getName());
            portfolio.setVisible(false);
            portfolio.setUser(user);
            return portfolioService.createPortfolio(portfolio);
        }
        System.out.println("El username no existe...");
        return portfolio;
    }
    
    @GetMapping ("listportfolios/{username}")
    public List<PortfolioData> getUserPortfolios
        (@PathVariable String username) {
        // Devuelve lista de portfolios del usuario (sólo id y name)
        User user = userService.findUserByUsername(username);
        List<PortfolioData> portfolios = new ArrayList<> ();
        if (user!=null) {
            List<Portfolio> userPortfolios = new ArrayList<> (user.getPortfolioSet());
            for (int i = 0; i < userPortfolios.size(); i++) {
                portfolios.add(new PortfolioData());
                PortfolioData portfolio = portfolios.get(i);
                Portfolio userPortfolio = userPortfolios.get(i);
                portfolio.setId(userPortfolio.getId());
                portfolio.setName(userPortfolio.getName());
            }
        } else System.out.println("El username no existe...");
        return portfolios;
    }
    
    @PatchMapping ("updateportfolioname")
    public Portfolio updatePortfolioName (@RequestBody PortfolioData data) {
        // Actualiza name del portfolio que corresponde al id recibido
        Portfolio portfolio = portfolioService.findPortfolioById(data.getId());
        if (portfolio!=null) {
            if (!data.getName().isEmpty()) portfolio.setName(data.getName());
            return portfolioService.updatePortfolio(portfolio);
        }
        System.out.println("El portfolio no existe...");
        return portfolio;
    }
    
    @DeleteMapping ("deleteportfolio/{portfolio_id}")
    public void deletePortfolio (@PathVariable Long portfolio_id) {
        // Elimina portfolio que corresponde al id recibido
        // actualizando lista del usuario correspondiente y
        // eliminando los items que referencian al portfolio
        Portfolio portfolio = portfolioService.findPortfolioById(portfolio_id);
        if (portfolio!=null) {
            portfolioService.deletePortfolio(portfolio);
            System.out.println("Portfolio " + portfolio.getName() + " eliminado!");
        } else System.out.println("El portfolio no existe...");
    }
    
    
    /* Portfolio --> SocialType */
    
    @PostMapping ("addsocialtype")
    public SocialType createSocialType (@RequestBody SocialType data) {
        // Agrega un tipo de red social, asignando null a id
        SocialType socialType  = new SocialType();
        socialType.setId(null);
        socialType.setName(data.getName());
        socialType.setIconUrl(data.getIconUrl());
        return socialTypeService.createSocialType(socialType);
    }
    
    @GetMapping ("socialtypes")
    public List<SocialType> getSocialTypes () {
        // Devuelve todos los tipos de redes sociales registradas
        return socialTypeService.getSocialTypes();
    }
    
    @PutMapping ("updatesocialtype")
    public SocialType updateSocialType (@RequestBody SocialType data) {
        // Actualiza SocialType que corresponde al id recibido
        SocialType socialType = socialTypeService.findSocialTypeById(data.getId());
        if (socialType!=null) {
            socialType.setName(data.getName());
            socialType.setIconUrl(data.getIconUrl());
            return socialTypeService.updateSocialType(socialType);
        }
        System.out.println("El Social type no existe...");
        return socialType;
    }
    
    @DeleteMapping ("deletesocialtype/{socialtype_id}")
    public void deleteSocialType (@PathVariable Long socialtype_id) {
        // Elimina SocialType correspondiente al id de la ruta
        // y todos los elementos Social que lo referencien
        SocialType socialType = socialTypeService.findSocialTypeById(socialtype_id);
        if (socialType!=null) {
            socialTypeService.deleteSocialType(socialType);
            System.out.println("SocialType " + socialType.getName() + " eliminado!");
        } else System.out.println("El Social type no existe...");
    }
    
}
