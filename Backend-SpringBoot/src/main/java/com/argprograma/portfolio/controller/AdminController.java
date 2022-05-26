package com.argprograma.portfolio.controller;

import com.argprograma.portfolio.dto.Message;
import com.argprograma.portfolio.dto.PortfolioData;
import com.argprograma.portfolio.dto.PortfolioOut;
import com.argprograma.portfolio.dto.SocialTypeData;
import com.argprograma.portfolio.dto.UserData;
import com.argprograma.portfolio.dto.UserOut;
import com.argprograma.portfolio.model.Portfolio;
import com.argprograma.portfolio.model.Social;
import com.argprograma.portfolio.model.SocialType;
import com.argprograma.portfolio.model.User;
import com.argprograma.portfolio.service.IPortfolioService;
import com.argprograma.portfolio.service.ISocialTypeService;
import com.argprograma.portfolio.service.IUserService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="*")
public class AdminController {
    
    @Autowired
    private IUserService userService;
    @Autowired
    private IPortfolioService portfolioService;
    @Autowired
    private ISocialTypeService socialTypeService;
    @Autowired
    PasswordEncoder passwordEncoder;
    
    /* User */
    
    @PostMapping ("user/add")
    public ResponseEntity<?> createUser (@RequestBody UserData data) {
        // Agrega usuario, pasando 'null' en 'id'
        if (userService.existsByUsername(data.getUsername()))
            return new ResponseEntity(new Message("The username exists..."), HttpStatus.BAD_REQUEST);
        if (data.getPassword().isEmpty())
            return new ResponseEntity(new Message("Password cannot be empty..."), HttpStatus.BAD_REQUEST);
        if (data.getFirstName().isEmpty() || data.getLastName().isEmpty() || data.getEmail().isEmpty())
            return new ResponseEntity(new Message("Some fields are empty..."), HttpStatus.BAD_REQUEST);
        User user = new User();
        user.setId(null);
        user.setUsername(data.getUsername());
        user.setPassword(passwordEncoder.encode(data.getPassword()));
        user.setFirstName(data.getFirstName());
        user.setLastName(data.getLastName());
        user.setEmail(data.getEmail());
        user = userService.createUser(user);
        user.setPassword(null);
        return new ResponseEntity(user, HttpStatus.CREATED);
    }
    
    @GetMapping ("user/list")
    @PreAuthorize("authentication.principal.username == 'prulec'")
    public List<String> getUsers () {
        // Lista con los username existentes
        List<String> usernames = new ArrayList<> ();
        List<User> users = userService.getUsers();
        for (int i = 0; i < users.size(); i++) {
            usernames.add(users.get(i).getUsername());
        }
        return usernames;
    }
    
    @GetMapping ("user/get/{username}")
    @PreAuthorize("authentication.principal.username == 'prulec'"
            + " or authentication.principal.username == #username")
    public UserOut getUser (@PathVariable String username) {
        // Muestra datos de usuario con la lista de portfolios
        User user = userService.findUserByUsername(username);
        if (user!=null) {
            return new UserOut(user);
        } else System.out.println("El username no existe...");
        return null;
    }
    
    @PatchMapping ("user/update")
    @PreAuthorize("authentication.principal.username == 'prulec'"
            + " or authentication.principal.username == #username")
    public UserOut updateUser (@RequestBody UserData data) {
        // Actualiza datos de usuario que corresponda con id recibida
        User user = userService.findUserById(data.getId());
        if (user!=null) {
            if (!data.getUsername().isEmpty()) user.setUsername(data.getUsername());
            if (!data.getPassword().isEmpty()) user.setPassword(passwordEncoder.encode(data.getPassword()));
            if (!data.getFirstName().isEmpty()) user.setFirstName(data.getFirstName());
            if (!data.getLastName().isEmpty()) user.setLastName(data.getLastName());
            if (!data.getEmail().isEmpty()) user.setEmail(data.getEmail());
            return new UserOut (userService.updateUser(user));
        } else System.out.println("El User no existe...");
        return null;
    }
    
    @DeleteMapping ("user/delete/{username}")
    @PreAuthorize("authentication.principal.username == 'prulec'"
            + " or authentication.principal.username == #username")
    public void deleteUser (@PathVariable String username) {
        // Borra usuario y todos los portfolios que le pertenecen
        User user = userService.findUserByUsername(username);
        if (user!=null) {
            for (Portfolio portfolio : user.getPortfolioSet()) {
                for (Social social : portfolio.getSocialSet()) {
                    social.getSocialType().getSocialSet().remove(social);
                }
            }
            userService.deleteUser(user);
            System.out.println("Usuario " + user.getUsername() + " eliminado!");
        } else System.out.println("El username no existe...");
    }
    
    
    /* Portfolio */
    
    @PostMapping ("portfolio/add")
    @PreAuthorize("authentication.principal.username == 'prulec'"
            + " or authentication.principal.username == #data.username")
    public PortfolioOut createPortfolio (@RequestBody PortfolioData data) {
        // Agrega portfolio recibiendo username, y asignando: 
        // id=null, name (data), visible=false, user buscando por username
        User user = userService.findUserByUsername(data.getUsername());
        Portfolio portfolio = new Portfolio();
        if (user!=null) {
            portfolio.setId(null);
            portfolio.setName(data.getName());
            portfolio.setVisible(false);
            portfolio.setUser(user);
            return new PortfolioOut(portfolioService.createPortfolio(portfolio));
        }
        System.out.println("El username no existe...");
        return null;
    }
    
    @GetMapping ("portfolio/list/{username}")
    @PreAuthorize("authentication.principal.username == 'prulec'"
            + " or authentication.principal.username == #username")
    public List<PortfolioData> getUserPortfolios (@PathVariable String username) {
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
            return portfolios;
        } else System.out.println("El username no existe...");
        return null;
    }
    
    @PatchMapping ("portfolio/updatename")
    @PreAuthorize("authentication.principal.username == 'prulec'"
            + " or authentication.principal.username == #data.username")
    public PortfolioOut updatePortfolioName (@RequestBody PortfolioData data) {
        // Actualiza name del portfolio que corresponde al id recibido
        Portfolio portfolio = portfolioService.findPortfolioById(data.getId());
        if (portfolio!=null && portfolio.getUser().getUsername().equals(data.getUsername())) {
            if (!data.getName().isEmpty()) 
                portfolio.setName(data.getName());
            return new PortfolioOut(portfolioService.updatePortfolio(portfolio));
        }
        System.out.println("El portfolio no existe...");
        return null;
    }
    
    @DeleteMapping ("portfolio/delete/{username}/{portfolio_name}")
    @PreAuthorize("authentication.principal.username == 'prulec'"
            + " or authentication.principal.username == #username")
    public void deletePortfolio (@PathVariable String username,
                                 @PathVariable String portfolio_name) {
        // Elimina portfolio que corresponde al portfolio_name recibido
        // actualizando lista del usuario correspondiente y
        // eliminando los items que referencian al portfolio
        Portfolio portfolio = portfolioService.findPortfolioByName(portfolio_name);
        if (portfolio!=null && portfolio.getUser().getUsername().equals(username)) {
            for (Social social : portfolio.getSocialSet()) {
                social.getSocialType().getSocialSet().remove(social);
            }
            portfolioService.deletePortfolio(portfolio);
            System.out.println("Portfolio " + portfolio.getName() + " eliminado!");
        } else System.out.println("El portfolio no existe...");
    }
    
    
    /* SocialType */
    
    @PostMapping ("socialtype/add")
    @PreAuthorize("authentication.principal.username == 'prulec'")
    public SocialType createSocialType (@RequestBody SocialTypeData data) {
        // Agrega un tipo de red social, asignando null a id
        SocialType socialType  = new SocialType();
        socialType.setId(null);
        socialType.setName(data.getName());
        socialType.setIconUrl(data.getIconUrl());
        return socialTypeService.createSocialType(socialType);
    }
    
    @GetMapping ("socialtype/list")
    public List<SocialTypeData> getSocialTypes () {
        // Devuelve todos los tipos de redes sociales registradas
        List<SocialType> socialTypes = new ArrayList<>(socialTypeService.getSocialTypes());
        List<SocialTypeData> socialTypesData = new ArrayList<>();
        for (int i = 0; i < socialTypes.size(); i++) {
            socialTypesData.add(new SocialTypeData());
            SocialTypeData socialTypeData = socialTypesData.get(i);
            SocialType socialType = socialTypes.get(i);
            socialTypeData.setId(socialType.getId());
            socialTypeData.setName(socialType.getName());
            socialTypeData.setIconUrl(socialType.getIconUrl());
        }
        return socialTypesData;
    }
    
    @PutMapping ("socialtype/update")
    @PreAuthorize("authentication.principal.username == 'prulec'")
    public SocialTypeData updateSocialType (@RequestBody SocialTypeData data) {
        // Actualiza SocialType que corresponde al id recibido
        SocialType socialType = socialTypeService.findSocialTypeById(data.getId());
        SocialTypeData socialTypeData = new SocialTypeData();
        if (socialType!=null) {
            socialType.setName(data.getName());
            socialType.setIconUrl(data.getIconUrl());
            socialType = socialTypeService.updateSocialType(socialType);
            socialTypeData.setId(socialType.getId());
            socialTypeData.setName(socialType.getName());
            socialTypeData.setIconUrl(socialType.getIconUrl());
            return socialTypeData;
        } else System.out.println("El Social type no existe...");
        return null;
    }
    
    @DeleteMapping ("socialtype/delete/{socialtype_id}")
    @PreAuthorize("authentication.principal.username == 'prulec'")
    public void deleteSocialType (@PathVariable Long socialtype_id) {
        // Elimina SocialType correspondiente al id de la ruta
        // y todos los elementos Social que lo referencien
        SocialType socialType = socialTypeService.findSocialTypeById(socialtype_id);
        if (socialType!=null) {
            for (Social social : socialType.getSocialSet()) {
                social.getPortfolio().getSocialSet().remove(social);
            }
            socialTypeService.deleteSocialType(socialType);
            System.out.println("SocialType " + socialType.getName() + " eliminado!");
        } else System.out.println("El Social type no existe...");
    }
    
}
