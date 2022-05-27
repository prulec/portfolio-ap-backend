import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { EducationData } from 'src/app/model/EducationData';
import { ExperienceData } from 'src/app/model/ExperienceData';
import { HeaderAboutData } from 'src/app/model/HeaderAboutData';
import { Portfolio } from 'src/app/model/Portfolio';
import { PORTFOLIO } from 'src/app/constants/PORTFOLIO_CONST';
import { ProjectData } from 'src/app/model/ProjectData';
import { ProjectImageData } from 'src/app/model/ProjectImageData';
import { PortfolioService } from 'src/app/services/portfolio.service';
import { SkillData } from 'src/app/model/SkillData';
import { UserData } from 'src/app/model/UserData';
import { Project } from 'src/app/model/Project';

@Component({
  selector: 'app-edit-item',
  templateUrl: './edit-item.component.html',
  styleUrls: ['./edit-item.component.css']
})
export class EditItemComponent implements OnInit {

  banner_url_string:string = "banner_url";
  photo_url_string:string = "photo_url";
  job_title_string:string = "job_title";
  p_statement_string:string = "p_statement";

  @Input() title:string = "";
  @Input() portfolio:Portfolio = PORTFOLIO;
  @Input() project:Project = PORTFOLIO.projectList[0];
  @Input() item:any;
  @Output() onClose:EventEmitter<any> = new EventEmitter();
  dataHA:HeaderAboutData = {field:"", value:"", username:"", portfolioName:""};
  dataU:UserData = {id:BigInt(0),username:"",password:"",firstName:"",lastName:"",email:""};

  constructor(private portfolioService:PortfolioService) { }

  ngOnInit(): void {
  }

  onSubmit() {
    //VALIDAR
    switch (this.title) {
      case "Banner url":
        this.dataHA.field = this.banner_url_string;
        this.dataHA.value = this.portfolio.bannerUrl;
        this.dataHA.username = this.portfolio.user.username;
        this.dataHA.portfolioName = this.portfolio.name;
        this.portfolioService.updateHeaderAboutField(this.dataHA).subscribe();
        this.dataHA = {field:"", value:"", username:"", portfolioName:""};
        break;
      case "Photo url":
        this.dataHA.field = this.photo_url_string;
        this.dataHA.value = this.portfolio.photoUrl;
        this.dataHA.username = this.portfolio.user.username;
        this.dataHA.portfolioName = this.portfolio.name;
        this.portfolioService.updateHeaderAboutField(this.dataHA).subscribe();
        this.dataHA = {field:"", value:"", username:"", portfolioName:""};
        break;
      case "Job title":
        this.dataHA.field = this.job_title_string;
        this.dataHA.value = this.portfolio.jobTitle;
        this.dataHA.username = this.portfolio.user.username;
        this.dataHA.portfolioName = this.portfolio.name;
        this.portfolioService.updateHeaderAboutField(this.dataHA).subscribe();
        this.dataHA = {field:"", value:"", username:"", portfolioName:""};
        break;
      case "Personal statement":
        this.dataHA.field = this.p_statement_string;
        this.dataHA.value = this.portfolio.pstatement;
        this.dataHA.username = this.portfolio.user.username;
        this.dataHA.portfolioName = this.portfolio.name;
        this.portfolioService.updateHeaderAboutField(this.dataHA).subscribe();
        this.dataHA = {field:"", value:"", username:"", portfolioName:""};
        break;
      case "Fullname":
        this.dataU.id = this.portfolio.user.id;
        this.dataU.firstName = this.portfolio.user.firstName;
        this.dataU.lastName = this.portfolio.user.lastName;
        this.portfolioService.updateUserData(this.dataU).subscribe();
        this.dataU = {id:BigInt(0),username:"",password:"",firstName:"",lastName:"",email:""};
        break;
      case "Email":
        this.dataU.id = this.portfolio.user.id;
        this.dataU.email = this.portfolio.user.email;
        this.portfolioService.updateUserData(this.dataU).subscribe();
        this.dataU = {id:BigInt(0),username:"",password:"",firstName:"",lastName:"",email:""};
        break;
      case "Experience":
        let experienceData:ExperienceData = {
          id: this.item.id,
          logoUrl: this.item.logoUrl,
          enterprise: this.item.enterprise,
          experienceTime: this.item.experienceTime,
          position: this.item.position,
          tasks: this.item.tasks,
          username: this.portfolio.user.username,
          portfolioName: this.portfolio.name
        };
        this.portfolioService.updateItem(experienceData,"experience").subscribe();
        break;
      case "Education":
        let educationData:EducationData = {
          id: this.item.id,
          logoUrl: this.item.logoUrl,
          institution: this.item.institution,
          educationTime: this.item.educationTime,
          title: this.item.title,
          username: this.portfolio.user.username,
          portfolioName: this.portfolio.name
        };
        this.portfolioService.updateItem(educationData,"education").subscribe();
        break;
      case "Skill":
        let skillData:SkillData = {
          id: this.item.id,
          name: this.item.name,
          skillLevel: this.item.skillLevel,
          levelTag: this.item.levelTag,
          username: this.portfolio.user.username,
          portfolioName: this.portfolio.name
        };
        this.portfolioService.updateItem(skillData,"skill").subscribe();
        break;
      case "Project":
        let projectData:ProjectData = {
          id: this.item.id,
          name: this.item.name,
          projectTime: this.item.projectTime,
          link: this.item.link,
          description: this.item.description,
          username: this.portfolio.user.username,
          portfolioName: this.portfolio.name
        };
        this.portfolioService.updateItem(projectData,"project").subscribe();
        break;
      case "Project Image":
        let projectImageData:ProjectImageData = {
          id: this.item.id,
          title: this.item.title,
          imageUrl: this.item.imageUrl,
          username: this.portfolio.user.username,
          projectId: this.project.id
        };
        this.portfolioService.updateItem(projectImageData,"image").subscribe();
        break;
      default:
        break;
    }
    this.onClose.emit();
  }

  onDiscard() {
    this.onClose.emit();
  }

}
