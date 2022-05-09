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
import com.argprograma.portfolio.model.Education;
import com.argprograma.portfolio.model.Experience;
import com.argprograma.portfolio.model.Portfolio;
import com.argprograma.portfolio.model.Project;
import com.argprograma.portfolio.model.ProjectImage;
import com.argprograma.portfolio.model.Skill;
import com.argprograma.portfolio.model.Social;
import com.argprograma.portfolio.model.User;
import com.argprograma.portfolio.service.EducationService;
import com.argprograma.portfolio.service.ExperienceService;
import com.argprograma.portfolio.service.PortfolioService;
import com.argprograma.portfolio.service.ProjectImageService;
import com.argprograma.portfolio.service.ProjectService;
import com.argprograma.portfolio.service.SkillService;
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
    @Autowired
    private ExperienceService experienceService;
    @Autowired
    private EducationService educationService;
    @Autowired
    private SkillService skillService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProjectImageService projectImageService;
    
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
    public Experience addExperience (@PathVariable String portfolio_name,
                                     @RequestBody ExperienceData data) {
        Portfolio portfolio = portfolioService.findPortfolioByName(portfolio_name);
        Experience experience = new Experience();
        if (portfolio!=null) {
            experience.setId(null);
            experience.setItemOrder(portfolio.getExperienceSet().size()+1);
            experience.setLogoUrl(data.getLogoUrl());
            experience.setEnterprise(data.getEnterprise());
            experience.setExperienceTime(data.getExperienceTime());
            experience.setPosition(data.getPosition());
            experience.setTasks(data.getTasks());
            experience.setPortfolio(portfolio);
            return experienceService.createExperience(experience);
        }
        System.out.println("El Portfolio con ese name no existe...");
        return experience;
    }
    
    @PostMapping ("{portfolio_name}/education/add")
    public Education addEducation (@PathVariable String portfolio_name,
                                   @RequestBody EducationData data) {
        Portfolio portfolio = portfolioService.findPortfolioByName(portfolio_name);
        Education education = new Education();
        if (portfolio!=null) {
            education.setId(null);
            education.setItemOrder(portfolio.getEducationSet().size()+1);
            education.setLogoUrl(data.getLogoUrl());
            education.setInstitution(data.getInstitution());
            education.setEducationTime(data.getEducationTime());
            education.setTitle(data.getTitle());
            education.setPortfolio(portfolio);
            return educationService.createEducation(education);
        }
        System.out.println("El Portfolio con ese name no existe...");
        return education;
    }
    
    @PostMapping ("{portfolio_name}/skill/add")
    public Skill addSkill (@PathVariable String portfolio_name,
                           @RequestBody SkillData data) {
        Portfolio portfolio = portfolioService.findPortfolioByName(portfolio_name);
        Skill skill = new Skill();
        if (portfolio!=null) {
            skill.setId(null);
            skill.setItemOrder(portfolio.getSkillSet().size()+1);
            skill.setName(data.getName());
            skill.setSkillLevel(data.getSkillLevel());
            skill.setLevelTag(data.getLevelTag());
            skill.setPortfolio(portfolio);
            return skillService.createSkill(skill);
        }
        System.out.println("El Portfolio con ese name no existe...");
        return skill;
    }
    
    @PostMapping ("{portfolio_name}/project/add")
    public Project addProject (@PathVariable String portfolio_name,
                               @RequestBody ProjectData data) {
        Portfolio portfolio = portfolioService.findPortfolioByName(portfolio_name);
        Project project = new Project();
        if (portfolio!=null) {
            project.setId(null);
            project.setItemOrder(portfolio.getProjectSet().size()+1);
            project.setName(data.getName());
            project.setProjectTime(data.getProjectTime());
            project.setLink(data.getLink());
            project.setDescription(data.getDescription());
            project.setPortfolio(portfolio);
            return projectService.createProject(project);
        }
        System.out.println("El Portfolio con ese name no existe...");
        return project;
    }
    
    @PostMapping ("{project_id}/image/add")
    public ProjectImage addProjectImage (@PathVariable Long project_id,
                                         @RequestBody ProjectImageData data) {
        Project project = projectService.findProjectById(project_id);
        ProjectImage projectImage = new ProjectImage();
        if (project!=null) {
            projectImage.setId(null);
            projectImage.setItemOrder(project.getProjectImageSet().size()+1);
            projectImage.setTitle(data.getTitle());
            projectImage.setImageUrl(data.getImageUrl());
            projectImage.setProject(project);
            return projectImageService.createProjectImage(projectImage);
        }
        System.out.println("El Proyecto no existe...");
        return projectImage;
    }
    
    
    /* Update Item */
    
    @PatchMapping ("{portfolio_name}/social/update")
    public Social updateSocial (@PathVariable String portfolio_name,
                                @RequestBody SocialData data) {
        return null;
    }
    
    @PatchMapping ("{portfolio_name}/experience/update")
    public Experience updateExperience (@PathVariable String portfolio_name,
                                        @RequestBody ExperienceData data) {
        return null;
    }
    
    @PatchMapping ("{portfolio_name}/education/update")
    public Education updateEducation (@PathVariable String portfolio_name,
                                      @RequestBody EducationData data) {
        return null;
    }
    
    @PatchMapping ("{portfolio_name}/skill/update")
    public Skill updateSkill (@PathVariable String portfolio_name,
                              @RequestBody SkillData data) {
        return null;
    }
    
    @PatchMapping ("{portfolio_name}/project/update")
    public Project updateProject (@PathVariable String portfolio_name,
                                  @RequestBody ProjectData data) {
        return null;
    }
    
    @PatchMapping ("{project_id}/image/update")
    public ProjectImage updateProjectImage (@PathVariable Long project_id,
                                            @RequestBody ProjectImageData data) {
        return null;
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
