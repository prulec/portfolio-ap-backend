import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { EducationData } from 'src/app/model/EducationData';
import { ExperienceData } from 'src/app/model/ExperienceData';
import { Portfolio } from 'src/app/model/Portfolio';
import { PORTFOLIO } from 'src/app/constants/PORTFOLIO_CONST';
import { Project } from 'src/app/model/Project';
import { ProjectData } from 'src/app/model/ProjectData';
import { ProjectImageData } from 'src/app/model/ProjectImageData';
import { PortfolioService } from 'src/app/services/portfolio.service';
import { SkillData } from 'src/app/model/SkillData';

@Component({
  selector: 'app-add-item',
  templateUrl: './add-item.component.html',
  styleUrls: ['./add-item.component.css']
})
export class AddItemComponent implements OnInit {

  @Input() title:string = "";
  @Input() section:string = "";
  @Input() list:any = [];
  @Input() project:Project = PORTFOLIO.projectList[0];
  @Input() portfolio:Portfolio = PORTFOLIO;
  @Output() onClose:EventEmitter<any> = new EventEmitter();
  @Output() onAdd:EventEmitter<any> = new EventEmitter();
  experienceData:ExperienceData = {
    logoUrl: "",
    enterprise: "",
    experienceTime: "",
    position: "",
    tasks: "",
    username: "",
    portfolioName: ""
  };
  educationData:EducationData = {
    logoUrl: "",
    institution: "",
    educationTime: "",
    title: "",
    username: "",
    portfolioName: ""
  };
  skillData:SkillData = {
    name: "",
    skillLevel: 0,
    levelTag: "",
    username: "",
    portfolioName: ""
  };
  projectData:ProjectData = {
    name: "",
    projectTime: "",
    link: "",
    description: "",
    username: "",
    portfolioName: ""
  };
  projectImageData: ProjectImageData = {
    title: "",
    imageUrl: "",
    username: "",
    projectId: BigInt(0)
  }


  constructor(private portfolioService:PortfolioService) { }

  ngOnInit(): void {
  }

  onSubmit(section:string){
    //VALIDAR
    switch (section) {
      case "experience":
        this.experienceData.username = this.portfolio.user.username;
        this.experienceData.portfolioName = this.portfolio.name;
        this.portfolioService.addItem(this.experienceData,section)
            .subscribe(i => {
              this.list.push(i);
              this.onAdd.emit(this.list);
            });
        break;
      case "education":
        this.educationData.username = this.portfolio.user.username;
        this.educationData.portfolioName = this.portfolio.name;
        this.portfolioService.addItem(this.educationData,section)
            .subscribe(i => {
              this.list.push(i);
              this.onAdd.emit(this.list);
            });
        break;
      case "skill":
        this.skillData.username = this.portfolio.user.username;
        this.skillData.portfolioName = this.portfolio.name;
        this.portfolioService.addItem(this.skillData,section)
            .subscribe(i => {
              this.list.push(i);
              this.onAdd.emit(this.list);
            });
        break;
      case "project":
        this.projectData.username = this.portfolio.user.username;
        this.projectData.portfolioName = this.portfolio.name;
        this.portfolioService.addItem(this.projectData,section)
            .subscribe(i => {
              this.list.push(i);
              this.onAdd.emit(this.list);
            });
        break;
      case "image":
        this.projectImageData.username = this.portfolio.user.username;
        this.projectImageData.projectId = this.project.id;
        this.portfolioService.addProjectImage(this.projectImageData)
            .subscribe(i => {
              this.list.push(i);
              this.onAdd.emit(this.list);
            });
        break;
      default:
        break;
    }
    this.onClose.emit();
  }

  onDiscard(){
    this.onClose.emit();
  }

}
