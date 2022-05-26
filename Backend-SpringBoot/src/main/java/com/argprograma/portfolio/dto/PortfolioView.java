package com.argprograma.portfolio.dto;

import com.argprograma.portfolio.model.Education;
import com.argprograma.portfolio.model.Experience;
import com.argprograma.portfolio.model.Portfolio;
import com.argprograma.portfolio.model.Project;
import com.argprograma.portfolio.model.ProjectImage;
import com.argprograma.portfolio.model.Skill;
import com.argprograma.portfolio.model.Social;
import com.argprograma.portfolio.model.SocialType;
import com.argprograma.portfolio.model.User;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PortfolioView {

    private Long id;
    private String name;
    private boolean visible;
    private String bannerUrl;
    private String photoUrl;
    private String jobTitle;
    private String pStatement;
    private UserView user;
    private List<SocialView> socialList = new ArrayList<>();
    private List<ExperienceView> experienceList = new ArrayList<>();
    private List<EducationView> educationList = new ArrayList<>();
    private List<SkillView> skillList = new ArrayList<>();
    private List<ProjectView> projectList = new ArrayList<>();

    public PortfolioView() {
    }

    public PortfolioView(Portfolio portfolio) {

        name = portfolio.getName();
        visible = portfolio.isVisible();
        bannerUrl = portfolio.getBannerUrl();
        photoUrl = portfolio.getPhotoUrl();
        jobTitle = portfolio.getJobTitle();
        pStatement = portfolio.getPStatement();

        user = new UserView(portfolio.getUser());

        for (Social social : portfolio.getSocialSet()) 
            socialList.add(new SocialView(social));        
        socialList.sort((p1, p2) -> p1.getItemOrder() - p2.getItemOrder());

        for (Experience experience : portfolio.getExperienceSet()) 
            experienceList.add(new ExperienceView(experience));
        experienceList.sort((p1, p2) -> p1.getItemOrder() - p2.getItemOrder());

        for (Education education : portfolio.getEducationSet()) 
            educationList.add(new EducationView(education));
        educationList.sort((p1, p2) -> p1.getItemOrder() - p2.getItemOrder());

        for (Skill skill : portfolio.getSkillSet()) 
            skillList.add(new SkillView(skill));
        skillList.sort((p1, p2) -> p1.getItemOrder() - p2.getItemOrder());

        for (Project project : portfolio.getProjectSet()) 
            projectList.add(new ProjectView(project));
        projectList.sort((p1, p2) -> p1.getItemOrder() - p2.getItemOrder());

    }

    @Getter @Setter
    private class UserView {
        private Long id;
        private String username;
        private String password;
        private String firstName;
        private String lastName;
        private String email;
        private List<PortfolioOut> portfolioList = new ArrayList<>();

        private UserView(User user) {
            username = user.getUsername();
            firstName = user.getFirstName();
            lastName = user.getLastName();
            email = user.getEmail();
        }
    }

    @Getter @Setter
    private class SocialTypeView {
        private Long id;
        private String name;
        private String iconUrl;

        private SocialTypeView (SocialType socialType) {
            name = socialType.getName();
            iconUrl = socialType.getIconUrl();
        }
    }

    @Getter @Setter
    private class SocialView {
        private Long id;
        private int itemOrder;
        private String url;
        private SocialTypeView socialTypeData;
        private PortfolioData portfolioData;

        private SocialView (Social social) {
            itemOrder = social.getItemOrder();
            url = social.getUrl();
            socialTypeData = new SocialTypeView(social.getSocialType());
        }
    }

    @Getter private class ExperienceView {
        private Long id;
        private int itemOrder;
        private String logoUrl;
        private String enterprise;
        private String experienceTime;
        private String position;
        private String tasks;
        private PortfolioData portfolioData;

        private ExperienceView (Experience experience) {
            itemOrder = experience.getItemOrder();
            logoUrl = experience.getLogoUrl();
            enterprise = experience.getEnterprise();
            experienceTime = experience.getExperienceTime();
            position = experience.getPosition();
            tasks = experience.getTasks();
        }
    }

    @Getter @Setter
    private class EducationView {
        private Long id;
        private int itemOrder;
        private String logoUrl;
        private String institution;
        private String educationTime;
        private String title;
        private PortfolioData portfolioData;

        private EducationView (Education education) {
            itemOrder = education.getItemOrder();
            logoUrl = education.getLogoUrl();
            institution = education.getInstitution();
            educationTime = education.getEducationTime();
            title = education.getTitle();
        }
    }

    @Getter @Setter
    private class SkillView {
        private Long id;
        private int itemOrder;
        private String name;
        private Integer skillLevel;
        private String levelTag;
        private PortfolioData portfolioData;

        private SkillView (Skill skill) {
            itemOrder = skill.getItemOrder();
            name = skill.getName();
            skillLevel = skill.getSkillLevel();
            levelTag = skill.getLevelTag();
        }
    }

    @Getter @Setter
    private class ProjectView {
        private Long id;
        private int itemOrder;
        private String name;
        private String projectTime;
        private String link;
        private String description;
        private List<ProjectImageView> projectImageList = new ArrayList<>();
        private PortfolioData portfolioData;

        private ProjectView (Project project) {
            itemOrder = project.getItemOrder();
            name = project.getName();
            projectTime = project.getProjectTime();
            link = project.getLink();
            description = project.getDescription();
            for (ProjectImage projectImage : project.getProjectImageSet()) 
                projectImageList.add(new ProjectImageView(projectImage));
            projectImageList.sort((p1,p2) -> p1.getItemOrder()-p2.getItemOrder());
        }
    }

    @Getter @Setter
    private class ProjectImageView {
        private Long id;
        private int itemOrder;
        private String title;
        private String imageUrl;
        private ProjectData projectData;

        private ProjectImageView (ProjectImage projectImage) {
            itemOrder = projectImage.getItemOrder();
            title = projectImage.getTitle();
            imageUrl = projectImage.getImageUrl();
        }
    }

}
