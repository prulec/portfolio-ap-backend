package com.argprograma.portfolio.controller;

import com.argprograma.portfolio.dto.EducationData;
import com.argprograma.portfolio.dto.ExperienceData;
import com.argprograma.portfolio.dto.EditUserData;
import com.argprograma.portfolio.dto.HeaderAboutData;
import com.argprograma.portfolio.dto.OrderData;
import com.argprograma.portfolio.dto.ProjectData;
import com.argprograma.portfolio.dto.ProjectImageData;
import com.argprograma.portfolio.dto.SkillData;
import com.argprograma.portfolio.dto.SocialData;
import com.argprograma.portfolio.model.Portfolio;
import com.argprograma.portfolio.model.Social;
import com.argprograma.portfolio.model.User;
import com.argprograma.portfolio.service.PortfolioService;
import com.argprograma.portfolio.service.SocialService;
import com.argprograma.portfolio.service.SocialTypeService;
import com.argprograma.portfolio.service.UserService;
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
public class EditController {
    
    @Autowired
    private PortfolioService portfolioService;
    @Autowired
    private UserService userService;
    @Autowired
    private SocialTypeService socialTypeService;
    @Autowired
    private SocialService socialService;
    
    /* Portfolio */
    
    @GetMapping ("{portfolio_name}")
    public Portfolio getPortfolio (@PathVariable String portfolio_name){
        // Traer el objeto completo Portfolio con el name del portfolio
        Portfolio portfolio = portfolioService.findPortfolioByName(portfolio_name);
        if (portfolio==null) System.out.println("No existe Portfolio con ese name..."); 
        return portfolio;
    }
    
    @PatchMapping ("{portfolio_name}/visibility")
    public Portfolio toggleVisibility (@PathVariable String portfolio_name){
        Portfolio portfolio = portfolioService.findPortfolioByName(portfolio_name);
        if (portfolio!=null) {
            portfolio.setVisible(!portfolio.isVisible());
            return portfolioService.updatePortfolio(portfolio);
        }
        System.out.println("No existe Portfolio con ese name...");
        return portfolio;
    }
    
    @PatchMapping ("{portfolio_name}/user/update")
    public Portfolio updateUserData (@PathVariable String portfolio_name,
                                  @RequestBody EditUserData data) {
        // Edición de datos de usuario en el modo Edit: fullname y email
        Portfolio portfolio = portfolioService.findPortfolioByName(portfolio_name);
        if (portfolio!=null) {
            User user = portfolio.getUser();
            if (!data.getFirstName().isEmpty()) user.setFirstName(data.getFirstName());
            if (!data.getLastName().isEmpty()) user.setLastName(data.getLastName());
            if (!data.getEmail().isEmpty()) user.setEmail(data.getEmail());
            userService.updateUser(user);
        } else System.out.println("No existe Portfolio con ese name...");
        return portfolio;
    }
    
    @PutMapping ("{portfolio_name}/header-about/update")
    public Portfolio updateHeaderAbout 
        (@PathVariable String portfolio_name,
         @RequestBody HeaderAboutData data) {
        // Edición de un campo del Header o de la sección About
        // excepto Visibilidad del portfolio, Nombre completo de usuario
        // y Redes sociales, serían: Url del banner, Url de la foto, 
        // Título profesional, Breve descripción personal
        Portfolio portfolio = portfolioService.findPortfolioByName(portfolio_name);
        if (portfolio!=null) {
            switch (data.getField()) {
                case "banner_url" -> portfolio.setBannerUrl(data.getValue());
                case "photo_url" -> portfolio.setPhotoUrl(data.getValue());
                case "job_title" -> portfolio.setJobTitle(data.getValue());
                case "p_statement" -> portfolio.setPStatement(data.getValue());
                default -> {
                    System.out.println("El field ingresado es incorrecto...");
                    return portfolio;
                }
            }
            return portfolioService.updatePortfolio(portfolio);
        }
        System.out.println("No existe Portfolio con ese name...");
        return portfolio;
    }
        
        
    /* Add Item */
    
    @PostMapping ("{portfolio_name}/social/add")
    public Social addSocial (@PathVariable String portfolio_name,
                                @RequestBody SocialData data) {
        Portfolio portfolio = portfolioService.findPortfolioByName(portfolio_name);
        Social social = new Social();
        if (portfolio!=null) {
            social.setId(null);
            social.setItemOrder(portfolio.getSocialSet().size()+1);
            social.setUrl(data.getUrl());
            social.setSocialType(socialTypeService.findSocialTypeByName(data.getSocialTypeName()));
            social.setPortfolio(portfolio);
            return socialService.createSocial(social);
        }
        System.out.println("El Portfolio con ese name no existe...");
        return social;
    }
    
    @PostMapping ("{portfolio_name}/experience/add")
    public String addExperience (@PathVariable String portfolio_name,
                                 @RequestBody ExperienceData data) {
        return "Experience added";
    }
    
    @PostMapping ("{portfolio_name}/education/add")
    public String addEducation (@PathVariable String portfolio_name,
                                @RequestBody EducationData data) {
        return "Education added";
    }
    
    @PostMapping ("{portfolio_name}/skill/add")
    public String addSkill (@PathVariable String portfolio_name,
                            @RequestBody SkillData data) {
        return "Skill added";
    }
    
    @PostMapping ("{portfolio_name}/project/add")
    public String addProject (@PathVariable String portfolio_name,
                              @RequestBody ProjectData data) {
        return "Project added";
    }
    
    @PostMapping ("{project_id}/image/add")
    public String addProjectImage (@PathVariable Long project_id,
                                   @RequestBody ProjectImageData data) {
        return "Project image added";
    }
    
    
    /* Update Item */
    
    @PatchMapping ("{portfolio_name}/social/update")
    public String updateSocial (@PathVariable String portfolio_name,
                                @RequestBody SocialData data) {
        return "Social updated";
    }
    
    @PatchMapping ("{portfolio_name}/experience/update")
    public String updateExperience (@PathVariable String portfolio_name,
                                    @RequestBody ExperienceData data) {
        return "Experience updated";
    }
    
    @PatchMapping ("{portfolio_name}/education/update")
    public String updateEducation (@PathVariable String portfolio_name,
                                   @RequestBody EducationData data) {
        return "Education updated";
    }
    
    @PatchMapping ("{portfolio_name}/skill/update")
    public String updateSkill (@PathVariable String portfolio_name,
                               @RequestBody SkillData data) {
        return "Skill updated";
    }
    
    @PatchMapping ("{portfolio_name}/project/update")
    public String updateProject (@PathVariable String portfolio_name,
                                 @RequestBody ProjectData data) {
        return "Project updated";
    }
    
    @PatchMapping ("{project_id}/image/update")
    public String updateProjectImage (@PathVariable Long project_id,
                                      @RequestBody ProjectImageData data) {
        return "Project image updated";
    }
    
    
    /* Delete Item */
    
    @DeleteMapping ("deleteitem/{section}/{id}")
    public void deleteItem (@PathVariable String section,
                              @PathVariable Long id) {
        // Secciones: Social, Experience, Education, Skills, 
        //            Projects, ProjectImages
    }
    
    
    /* Change order */
    
    @PatchMapping ("changeorder")
    public String changeOrderItem (@RequestBody OrderData data) {
        return "Order changed";
    }
    
}
