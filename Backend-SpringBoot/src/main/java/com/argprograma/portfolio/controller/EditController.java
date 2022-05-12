package com.argprograma.portfolio.controller;

import com.argprograma.portfolio.dto.EducationData;
import com.argprograma.portfolio.dto.ExperienceData;
import com.argprograma.portfolio.dto.EditUserData;
import com.argprograma.portfolio.dto.EducationOut;
import com.argprograma.portfolio.dto.ExperienceOut;
import com.argprograma.portfolio.dto.HeaderAboutData;
import com.argprograma.portfolio.dto.OrderData;
import com.argprograma.portfolio.dto.PortfolioOut;
import com.argprograma.portfolio.dto.ProjectData;
import com.argprograma.portfolio.dto.ProjectImageData;
import com.argprograma.portfolio.dto.ProjectImageOut;
import com.argprograma.portfolio.dto.ProjectOut;
import com.argprograma.portfolio.dto.SkillData;
import com.argprograma.portfolio.dto.SkillOut;
import com.argprograma.portfolio.dto.SocialData;
import com.argprograma.portfolio.dto.SocialOut;
import com.argprograma.portfolio.model.Education;
import com.argprograma.portfolio.model.Experience;
import com.argprograma.portfolio.model.Portfolio;
import com.argprograma.portfolio.model.Project;
import com.argprograma.portfolio.model.ProjectImage;
import com.argprograma.portfolio.model.Skill;
import com.argprograma.portfolio.model.Social;
import com.argprograma.portfolio.model.SocialType;
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
    
    @GetMapping ("{portfolio_name}/edit")
    public PortfolioOut getPortfolio (@PathVariable String portfolio_name){
        // Traer el objeto completo Portfolio con el name del portfolio
        Portfolio portfolio = portfolioService.findPortfolioByName(portfolio_name);
        if (portfolio!=null) return new PortfolioOut(portfolio);
        System.out.println("No existe Portfolio con ese name...");
        return null;
    }
    
    @PatchMapping ("{portfolio_name}/visibility")
    public PortfolioOut toggleVisibility (@PathVariable String portfolio_name){
        Portfolio portfolio = portfolioService.findPortfolioByName(portfolio_name);
        if (portfolio!=null) {
            portfolio.setVisible(!portfolio.isVisible());
            return new PortfolioOut(portfolioService.updatePortfolio(portfolio));
        }
        System.out.println("No existe Portfolio con ese name...");
        return null;
    }
    
    @PatchMapping ("{portfolio_name}/user/update")
    public PortfolioOut updateUserData (@PathVariable String portfolio_name,
                                        @RequestBody EditUserData data) {
        // Edición de datos de usuario en el modo Edit: fullname y email
        Portfolio portfolio = portfolioService.findPortfolioByName(portfolio_name);
        if (portfolio!=null) {
            User user = portfolio.getUser();
            if (!data.getFirstName().isEmpty()) user.setFirstName(data.getFirstName());
            if (!data.getLastName().isEmpty()) user.setLastName(data.getLastName());
            if (!data.getEmail().isEmpty()) user.setEmail(data.getEmail());
            userService.updateUser(user);
            return new PortfolioOut(portfolio);
        } else System.out.println("No existe Portfolio con ese name...");
        return null;
    }
    
    @PutMapping ("{portfolio_name}/header-about/update")
    public PortfolioOut updateHeaderAbout 
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
                    return null;
                }
            }
            return new PortfolioOut(portfolioService.updatePortfolio(portfolio));
        }
        System.out.println("No existe Portfolio con ese name...");
        return null;
    }
        
        
    /* Add Item */
    
    @PostMapping ("{portfolio_name}/social/add")
    public SocialOut addSocial (@PathVariable String portfolio_name,
                             @RequestBody SocialData data) {
        Portfolio portfolio = portfolioService.findPortfolioByName(portfolio_name);
        if (portfolio!=null) {
            Social social = new Social();
            social.setId(null);
            social.setItemOrder(portfolio.getSocialSet().size()+1);
            social.setUrl(data.getUrl());
            social.setSocialType(socialTypeService.findSocialTypeByName(data.getSocialTypeName()));
            social.setPortfolio(portfolio);
            return new SocialOut(socialService.createSocial(social));
        }
        System.out.println("El Portfolio con ese name no existe...");
        return null;
    }
    
    @PostMapping ("{portfolio_name}/experience/add")
    public ExperienceOut addExperience (@PathVariable String portfolio_name,
                                     @RequestBody ExperienceData data) {
        Portfolio portfolio = portfolioService.findPortfolioByName(portfolio_name);
        if (portfolio!=null) {
            Experience experience = new Experience();
            experience.setId(null);
            experience.setItemOrder(portfolio.getExperienceSet().size()+1);
            experience.setLogoUrl(data.getLogoUrl());
            experience.setEnterprise(data.getEnterprise());
            experience.setExperienceTime(data.getExperienceTime());
            experience.setPosition(data.getPosition());
            experience.setTasks(data.getTasks());
            experience.setPortfolio(portfolio);
            return new ExperienceOut(experienceService.createExperience(experience));
        }
        System.out.println("El Portfolio con ese name no existe...");
        return null;
    }
    
    @PostMapping ("{portfolio_name}/education/add")
    public EducationOut addEducation (@PathVariable String portfolio_name,
                                   @RequestBody EducationData data) {
        Portfolio portfolio = portfolioService.findPortfolioByName(portfolio_name);
        if (portfolio!=null) {
            Education education = new Education();
            education.setId(null);
            education.setItemOrder(portfolio.getEducationSet().size()+1);
            education.setLogoUrl(data.getLogoUrl());
            education.setInstitution(data.getInstitution());
            education.setEducationTime(data.getEducationTime());
            education.setTitle(data.getTitle());
            education.setPortfolio(portfolio);
            return new EducationOut(educationService.createEducation(education));
        }
        System.out.println("El Portfolio con ese name no existe...");
        return null;
    }
    
    @PostMapping ("{portfolio_name}/skill/add")
    public SkillOut addSkill (@PathVariable String portfolio_name,
                           @RequestBody SkillData data) {
        Portfolio portfolio = portfolioService.findPortfolioByName(portfolio_name);
        if (portfolio!=null) {
            Skill skill = new Skill();
            skill.setId(null);
            skill.setItemOrder(portfolio.getSkillSet().size()+1);
            skill.setName(data.getName());
            skill.setSkillLevel(data.getSkillLevel());
            skill.setLevelTag(data.getLevelTag());
            skill.setPortfolio(portfolio);
            return new SkillOut(skillService.createSkill(skill));
        }
        System.out.println("El Portfolio con ese name no existe...");
        return null;
    }
    
    @PostMapping ("{portfolio_name}/project/add")
    public ProjectOut addProject (@PathVariable String portfolio_name,
                               @RequestBody ProjectData data) {
        Portfolio portfolio = portfolioService.findPortfolioByName(portfolio_name);
        if (portfolio!=null) {
            Project project = new Project();
            project.setId(null);
            project.setItemOrder(portfolio.getProjectSet().size()+1);
            project.setName(data.getName());
            project.setProjectTime(data.getProjectTime());
            project.setLink(data.getLink());
            project.setDescription(data.getDescription());
            project.setPortfolio(portfolio);
            return new ProjectOut(projectService.createProject(project));
        }
        System.out.println("El Portfolio con ese name no existe...");
        return null;
    }
    
    @PostMapping ("{project_id}/image/add")
    public ProjectImageOut addProjectImage (@PathVariable Long project_id,
                                         @RequestBody ProjectImageData data) {
        Project project = projectService.findProjectById(project_id);
        if (project!=null) {
            ProjectImage projectImage = new ProjectImage();
            projectImage.setId(null);
            projectImage.setItemOrder(project.getProjectImageSet().size()+1);
            projectImage.setTitle(data.getTitle());
            projectImage.setImageUrl(data.getImageUrl());
            projectImage.setProject(project);
            return new ProjectImageOut(projectImageService.createProjectImage(projectImage));
        }
        System.out.println("El Proyecto no existe...");
        return null;
    }
    
    
    /* Update Item */
    
    @PatchMapping ("social/update")
    public SocialOut updateSocial (@RequestBody SocialData data) {
        Social social = socialService.findSocialById(data.getId());
        if (social!=null) {
            if (!data.getUrl().isEmpty()) social.setUrl(data.getUrl());
            if (!data.getSocialTypeName().isEmpty()) {
                SocialType socialType = socialTypeService.findSocialTypeByName(data.getSocialTypeName());
                if (socialType!=null) {
                    social.setSocialType(socialType);                    
                } else System.out.println("El Social type no existe...");
            }
            return new SocialOut(socialService.updateSocial(social));
        } else System.out.println("El Social no existe...");
        return null;
    }
    
    @PatchMapping ("experience/update")
    public ExperienceOut updateExperience (@RequestBody ExperienceData data) {
        Experience experience = experienceService.findExperienceById(data.getId());
        if (experience!=null) {
            if (!data.getLogoUrl().isEmpty()) experience.setLogoUrl(data.getLogoUrl());
            if (!data.getEnterprise().isEmpty()) experience.setEnterprise(data.getEnterprise());
            if (!data.getExperienceTime().isEmpty()) experience.setExperienceTime(data.getExperienceTime());
            if (!data.getPosition().isEmpty()) experience.setPosition(data.getPosition());
            if (!data.getTasks().isEmpty()) experience.setTasks(data.getTasks());
            return new ExperienceOut(experienceService.updateExperience(experience));
        } else System.out.println("La Experience no existe...");
        return null;
    }
    
    @PatchMapping ("education/update")
    public EducationOut updateEducation (@RequestBody EducationData data) {
        Education education = educationService.findEducationById(data.getId());
        if (education!=null) {
            if (!data.getLogoUrl().isEmpty()) education.setLogoUrl(data.getLogoUrl());
            if (!data.getInstitution().isEmpty()) education.setInstitution(data.getInstitution());
            if (!data.getEducationTime().isEmpty()) education.setEducationTime(data.getEducationTime());
            if (!data.getTitle().isEmpty()) education.setTitle(data.getTitle());
            return new EducationOut(educationService.updateEducation(education));
        } else System.out.println("La Education no existe...");
        return null;
    }
    
    @PatchMapping ("skill/update")
    public SkillOut updateSkill (@RequestBody SkillData data) {
        Skill skill = skillService.findSkillById(data.getId());
        if (skill!=null) {
            if (!data.getName().isEmpty()) skill.setName(data.getName());
            if (data.getSkillLevel()!=null) skill.setSkillLevel(data.getSkillLevel());
            if (!data.getLevelTag().isEmpty()) skill.setLevelTag(data.getLevelTag());
            return new SkillOut(skillService.updateSkill(skill));
        } else System.out.println("La Skill no existe...");
        return null;
    }
    
    @PatchMapping ("project/update")
    public ProjectOut updateProject (@RequestBody ProjectData data) {
        Project project = projectService.findProjectById(data.getId());
        if (project!=null) {
            if (!data.getName().isEmpty()) project.setName(data.getName());
            if (!data.getProjectTime().isEmpty()) project.setProjectTime(data.getProjectTime());
            if (!data.getLink().isEmpty()) project.setLink(data.getLink());
            if (!data.getDescription().isEmpty()) project.setDescription(data.getDescription());
            return new ProjectOut(projectService.updateProject(project));
        } else System.out.println("El Project no existe...");
        return null;
    }
    
    @PatchMapping ("image/update")
    public ProjectImageOut updateProjectImage (@RequestBody ProjectImageData data) {
        ProjectImage projectImage = projectImageService.findProjectImageById(data.getId());
        if (projectImage!=null) {
            if (!data.getTitle().isEmpty()) projectImage.setTitle(data.getTitle());
            if (!data.getImageUrl().isEmpty()) projectImage.setImageUrl(data.getImageUrl());
            return new ProjectImageOut(projectImageService.updateProjectImage(projectImage));
        } else System.out.println("La Project image no existe...");
        return null;
    }
    
    
    /* Delete Item */
    
    @DeleteMapping ("deleteitem/{section}/{id}")
    public void deleteItem (@PathVariable String section,
                            @PathVariable Long id) {
        // Secciones: Social, Experience, Education, Skills, 
        //            Projects, ProjectImages
        switch (section) {
            case "social" -> {
                Social social = socialService.findSocialById(id);
                if (social!=null) {
                    int last = social.getPortfolio().getSocialSet().size();
                    social = socialService.changeOrderSocial(social, last);
                    social.getSocialType().getSocialSet().remove(social);
                    portfolioService.disconnectSocial(social);
                    System.out.println(section + ": " + id + " eliminado!");
                } else System.out.println(section + " no existe...");
            }
            case "experience" -> {
                Experience experience = experienceService.findExperienceById(id);
                if (experience!=null) {
                    int last = experience.getPortfolio().getExperienceSet().size();
                    experience = experienceService.changeOrderExperience(experience, last);
                    portfolioService.disconnectExperience(experience);
                    System.out.println(section + ": " + id + " eliminado!");
                } else System.out.println(section + " no existe...");
            }
            case "education" -> {
                Education education = educationService.findEducationById(id);
                if (education!=null) {
                    int last = education.getPortfolio().getEducationSet().size();
                    education = educationService.changeOrderEducation(education, last);
                    portfolioService.disconnectEducation(education);
                    System.out.println(section + ": " + id + " eliminado!");
                } else System.out.println(section + " no existe...");
            }
            case "skill" -> {
                Skill skill = skillService.findSkillById(id);
                if (skill!=null) {
                    int last = skill.getPortfolio().getSkillSet().size();
                    skill = skillService.changeOrderSkill(skill, last);
                    portfolioService.disconnectSkill(skill);
                    System.out.println(section + ": " + id + " eliminado!");
                } else System.out.println(section + " no existe...");
            }
            case "project" -> {
                Project project = projectService.findProjectById(id);
                if (project!=null) {
                    int last = project.getPortfolio().getProjectSet().size();
                    project = projectService.changeOrderProject(project, last);
                    portfolioService.disconnectProject(project);
                    System.out.println(section + ": " + id + " eliminado!");
                } else System.out.println(section + " no existe...");
            }
            case "projectimage" -> {
                ProjectImage projectImage = projectImageService.findProjectImageById(id);
                if (projectImage!=null) {
                    int last = projectImage.getProject().getProjectImageSet().size();
                    projectImage = projectImageService.changeOrderProjectImage(projectImage, last);
                    projectService.disconnectProjectImage(projectImage);
                    System.out.println(section + ": " + id + " eliminado!");
                } else System.out.println(section + " no existe...");
            }
            default -> System.out.println("La section ingresada no es válida...");
        }
    }
    
    
    /* Change order */
    
    @PatchMapping ("changeorder")
    public String changeOrderItem (@RequestBody OrderData data) {
        switch (data.getSection()) {
            case "social" -> {
                Social social = socialService.findSocialById(data.getId());
                if (social!=null) {
                    int newOrder = data.getNewItemOrder();
                    if (newOrder>0 && newOrder<=social.getPortfolio().getSocialSet().size()) {
                        socialService.changeOrderSocial(social,data.getNewItemOrder());
                    } else {
                        return "El nuevo orden no es válido...";
                    }
                } else return data.getSection() + ", id " + data.getId() + " no existe...";
            }
            case "experience" -> {
                Experience experience = experienceService.findExperienceById(data.getId());
                if (experience!=null) {
                    int newOrder = data.getNewItemOrder();
                    if (newOrder>0 && newOrder<=experience.getPortfolio().getExperienceSet().size()) {
                        experienceService.changeOrderExperience(experience,data.getNewItemOrder());
                    } else {
                        return "El nuevo orden no es válido...";
                    }
                } else return data.getSection() + ", id " + data.getId() + " no existe...";
            }
            case "education" -> {
                Education education = educationService.findEducationById(data.getId());
                if (education!=null) {
                    int newOrder = data.getNewItemOrder();
                    if (newOrder>0 && newOrder<=education.getPortfolio().getEducationSet().size()) {
                        educationService.changeOrderEducation(education,data.getNewItemOrder());
                    } else {
                        return "El nuevo orden no es válido...";
                    }
                } else return data.getSection() + ", id " + data.getId() + " no existe...";
            }
            case "skill" -> {
                Skill skill = skillService.findSkillById(data.getId());
                if (skill!=null) {
                    int newOrder = data.getNewItemOrder();
                    if (newOrder>0 && newOrder<=skill.getPortfolio().getSkillSet().size()) {
                        skillService.changeOrderSkill(skill,data.getNewItemOrder());
                    } else {
                        return "El nuevo orden no es válido...";
                    }
                } else return data.getSection() + ", id " + data.getId() + " no existe...";
            }
            case "project" -> {
                Project project = projectService.findProjectById(data.getId());
                if (project!=null) {
                    int newOrder = data.getNewItemOrder();
                    if (newOrder>0 && newOrder<=project.getPortfolio().getProjectSet().size()) {
                        projectService.changeOrderProject(project,data.getNewItemOrder());
                    } else {
                        return "El nuevo orden no es válido...";
                    }
                } else return data.getSection() + ", id " + data.getId() + " no existe...";
            }
            case "projectimage" -> {
                ProjectImage projectImage = projectImageService.findProjectImageById(data.getId());
                if (projectImage!=null) {
                    int newOrder = data.getNewItemOrder();
                    if (newOrder>0 && newOrder<=projectImage.getProject().getProjectImageSet().size()) {
                        projectImageService.changeOrderProjectImage(projectImage,data.getNewItemOrder());
                    } else {
                        return "El nuevo orden no es válido...";
                    }
                } else return data.getSection() + ", id " + data.getId() + " no existe...";
            }
            default -> {
                return "La section ingresada no es válida...";
            }
        }
        return "Section: " + data.getSection() + ", Id: " + data.getId()
                + "\nNew order position: " + data.getNewItemOrder();
    }
    
}
