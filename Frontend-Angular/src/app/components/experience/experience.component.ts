import { CdkDragDrop, moveItemInArray } from '@angular/cdk/drag-drop';
import { Component, Input, OnInit } from '@angular/core';
import { Experience } from 'src/app/model/Experience';
import { OrderData } from 'src/app/model/OrderData';
import { Portfolio } from 'src/app/model/Portfolio';
import { PORTFOLIO } from 'src/app/constants/PORTFOLIO_CONST';
import { PortfolioService } from 'src/app/services/portfolio.service';

@Component({
  selector: 'app-experience',
  templateUrl: './experience.component.html',
  styleUrls: ['./experience.component.css']
})
export class ExperienceComponent implements OnInit {

  @Input() experienceList: Experience[] = [];
  @Input() portfolio:Portfolio = PORTFOLIO;
  addVisible:boolean = false;
  addTitle:string = "Experience";
  addSection:string = "experience";

  constructor(private portfolioService:PortfolioService) { }

  ngOnInit(): void {
  }

  openAddPopup (): void {
    this.addVisible = true;
  }

  closeAddPopup () {
    this.addVisible = false;
  }

  refresh(list:Experience[]){
    this.experienceList = list;
    console.log("experience added!");
  }

  removeItem(item:any){
    this.experienceList = this.experienceList.filter(i => i.id!=item.id);
  }

  drop (event:CdkDragDrop<Experience[]>){
    let orderData: OrderData = {
      id: this.experienceList[event.previousIndex].id,
      section: "experience",
      newItemOrder: event.currentIndex + 1
    }
    this.portfolioService.changeOrderItem(orderData).subscribe();
    moveItemInArray(this.experienceList,event.previousIndex,event.currentIndex);
  }

}
