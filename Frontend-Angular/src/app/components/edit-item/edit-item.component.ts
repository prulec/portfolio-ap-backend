import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { EditUserData } from 'src/app/EditUserData';
import { EducationData } from 'src/app/EducationData';
import { ExperienceData } from 'src/app/ExperienceData';
import { HeaderAboutData } from 'src/app/HeaderAboutData';
import { Portfolio } from 'src/app/Portfolio';
import { PORTFOLIO } from 'src/app/PORTFOLIO_CONST';
import { PortfolioService } from 'src/app/services/portfolio.service';

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
  @Input() item:any;
  @Output() onClose:EventEmitter<any> = new EventEmitter();
  dataHA:HeaderAboutData = {field:"", value:""};
  dataU:EditUserData = {};

  constructor(private portfolioService:PortfolioService) { }

  ngOnInit(): void {
  }

  onSubmit() {
    //VALIDAR
    switch (this.title) {
      case "Banner url":
        this.dataHA.field = this.banner_url_string;
        this.dataHA.value = this.portfolio.bannerUrl;
        this.portfolioService.updateHeaderAboutField(this.portfolio,this.dataHA).subscribe();
        this.dataHA = {field:"", value:""};
        break;
      case "Photo url":
        this.dataHA.field = this.photo_url_string;
        this.dataHA.value = this.portfolio.photoUrl;
        this.portfolioService.updateHeaderAboutField(this.portfolio,this.dataHA).subscribe();
        this.dataHA = {field:"", value:""};
        break;
      case "Job title":
        this.dataHA.field = this.job_title_string;
        this.dataHA.value = this.portfolio.jobTitle;
        this.portfolioService.updateHeaderAboutField(this.portfolio,this.dataHA).subscribe();
        this.dataHA = {field:"", value:""};
        break;
      case "Personal statement":
        this.dataHA.field = this.p_statement_string;
        this.dataHA.value = this.portfolio.pstatement;
        this.portfolioService.updateHeaderAboutField(this.portfolio,this.dataHA).subscribe();
        this.dataHA = {field:"", value:""};
        break;
      case "Fullname":
        this.dataU.firstName = this.portfolio.user.firstName;
        this.dataU.lastName = this.portfolio.user.lastName;
        this.dataU.email = "";
        this.portfolioService.updateUserData(this.portfolio,this.dataU).subscribe();
        this.dataU = {};
        break;
      case "Email":
        this.dataU.firstName = "";
        this.dataU.lastName = "";
        this.dataU.email = this.portfolio.user.email;
        this.portfolioService.updateUserData(this.portfolio,this.dataU).subscribe();
        this.dataU = {};
        break;
      case "Experience":
        let experienceData:ExperienceData = {
          id: this.item.id,
          logoUrl: this.item.logoUrl,
          enterprise: this.item.enterprise,
          experienceTime: this.item.experienceTime,
          position: this.item.position,
          tasks: this.item.tasks
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
        };
        this.portfolioService.updateItem(educationData,"education").subscribe();
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
