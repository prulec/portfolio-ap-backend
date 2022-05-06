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
    
    /* Portfolio */
    
    @GetMapping ("{portfolio_id}")
    public Portfolio getPortfolio (@PathVariable Long portfolio_id){
        // Traer el objeto completo Portfolio con la id especificada
        return null;
    }
    
    @PatchMapping ("{portfolio_id}/visibility")
    public String toggleVisibility (@PathVariable Long portfolio_id){
        return "Visibility on/off";
    }
    
    @PatchMapping ("{portfolio_id}/userdata/update")
    public String updateUserData (@RequestBody EditUserData data) {
        // Edición de datos de usuario en el modo Edit: fullname y email
        return "User data updated";
    }
    
    @PutMapping ("{portfolio_id}/header-about/update")
    public String updateHeaderAbout 
        (@PathVariable Long portfolio_id,
         @RequestBody HeaderAboutData data) {
        // Edición de un campo del Header o de la sección About
        // excepto Visibilidad del portfolio, Nombre completo de usuario
        // y Redes sociales
        return "Header&About updated";
    }
        
        
    /* Add Item */
    
    @PostMapping ("{portfolio_id}/social/add")
    public String addSocial (@PathVariable Long portfolio_id,
                             @RequestBody SocialData data) {
        return "Social added";
    }
    
    @PostMapping ("{portfolio_id}/experience/add")
    public String addExperience (@PathVariable Long portfolio_id,
                                 @RequestBody ExperienceData data) {
        return "Experience added";
    }
    
    @PostMapping ("{portfolio_id}/education/add")
    public String addEducation (@PathVariable Long portfolio_id,
                                @RequestBody EducationData data) {
        return "Education added";
    }
    
    @PostMapping ("{portfolio_id}/skill/add")
    public String addSkill (@PathVariable Long portfolio_id,
                            @RequestBody SkillData data) {
        return "Skill added";
    }
    
    @PostMapping ("{portfolio_id}/project/add")
    public String addProject (@PathVariable Long portfolio_id,
                              @RequestBody ProjectData data) {
        return "Project added";
    }
    
    @PostMapping ("{project_id}/image/add")
    public String addProjectImage (@PathVariable Long project_id,
                                   @RequestBody ProjectImageData data) {
        return "Project image added";
    }
    
    
    /* Update Item */
    
    @PatchMapping ("{portfolio_id}/social/update")
    public String updateSocial (@PathVariable Long portfolio_id,
                                @RequestBody SocialData data) {
        return "Social updated";
    }
    
    @PatchMapping ("{portfolio_id}/experience/update")
    public String updateExperience (@PathVariable Long portfolio_id,
                                    @RequestBody ExperienceData data) {
        return "Experience updated";
    }
    
    @PatchMapping ("{portfolio_id}/education/update")
    public String updateEducation (@PathVariable Long portfolio_id,
                                   @RequestBody EducationData data) {
        return "Education updated";
    }
    
    @PatchMapping ("{portfolio_id}/skill/update")
    public String updateSkill (@PathVariable Long portfolio_id,
                               @RequestBody SkillData data) {
        return "Skill updated";
    }
    
    @PatchMapping ("{portfolio_id}/project/update")
    public String updateProject (@PathVariable Long portfolio_id,
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
    public String deleteItem (@PathVariable String section,
                              @PathVariable Long id) {
        // Secciones: Social, Experience, Education, Skills, 
        //            Projects, ProjectImages
        return "Item deleted";
    }
    
    
    /* Change order */
    
    @PatchMapping ("changeorder")
    public String changeOrderItem (@RequestBody OrderData data) {
        return "Order changed";
    }
    
}
