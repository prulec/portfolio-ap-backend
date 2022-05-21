import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { EducationData } from 'src/app/EducationData';
import { ExperienceData } from 'src/app/ExperienceData';
import { PortfolioService } from 'src/app/services/portfolio.service';

@Component({
  selector: 'app-add-item',
  templateUrl: './add-item.component.html',
  styleUrls: ['./add-item.component.css']
})
export class AddItemComponent implements OnInit {

  @Input() title:string = "";
  @Input() section:string = "";
  @Input() list:any = [];
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
  }


  constructor(private portfolioService:PortfolioService) { }

  ngOnInit(): void {
  }

  onSubmit(section:string){
    //VALIDAR
    switch (section) {
      case "experience":
        this.portfolioService.addItem(this.experienceData,section)
            .subscribe(i => {
              this.list.push(i);
              this.onAdd.emit(this.list);
            });
        break;
      case "education":
        this.portfolioService.addItem(this.educationData,section)
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
