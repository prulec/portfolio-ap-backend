import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { EducationData } from 'src/app/EducationData';
import { ExperienceData } from 'src/app/ExperienceData';
import { Portfolio } from 'src/app/Portfolio';
import { PORTFOLIO } from 'src/app/PORTFOLIO_CONST';
import { Project } from 'src/app/Project';
import { ProjectData } from 'src/app/ProjectData';
import { ProjectImageData } from 'src/app/ProjectImageData';
import { PortfolioService } from 'src/app/services/portfolio.service';
import { SkillData } from 'src/app/SkillData';

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
    tasks: ""
  };
  educationData:EducationData = {
    logoUrl: "",
    institution: "",
    educationTime: "",
    title: "",
  };
  skillData:SkillData = {
    name: "",
    skillLevel: 0,
    levelTag: ""
  };
  projectData:ProjectData = {
    name: "",
    projectTime: "",
    link: "",
    description: ""
  };
  projectImageData: ProjectImageData = {
    title: "",
    imageUrl: ""
  }


  constructor(private portfolioService:PortfolioService) { }

  ngOnInit(): void {
  }

  onSubmit(section:string){
    //VALIDAR
    switch (section) {
      case "experience":
        this.portfolioService.addItem(this.portfolio,this.experienceData,section)
            .subscribe(i => {
              this.list.push(i);
              this.onAdd.emit(this.list);
            });
        break;
      case "education":
        this.portfolioService.addItem(this.portfolio,this.educationData,section)
            .subscribe(i => {
              this.list.push(i);
              this.onAdd.emit(this.list);
            });
        break;
      case "skill":
        this.portfolioService.addItem(this.portfolio,this.skillData,section)
            .subscribe(i => {
              this.list.push(i);
              this.onAdd.emit(this.list);
            });
        break;
      case "project":
        this.portfolioService.addItem(this.portfolio,this.projectData,section)
            .subscribe(i => {
              this.list.push(i);
              this.onAdd.emit(this.list);
            });
        break;
      case "image":
        this.portfolioService.addProjectImage(this.project,this.projectImageData)
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
