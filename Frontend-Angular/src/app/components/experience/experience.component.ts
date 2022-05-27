import { CdkDragDrop, moveItemInArray } from '@angular/cdk/drag-drop';
import { Component, Input, OnInit } from '@angular/core';
import { Experience } from 'src/app/model/Experience';
import { OrderData } from 'src/app/model/OrderData';
import { Portfolio } from 'src/app/model/Portfolio';
import { PORTFOLIO } from 'src/app/constants/PORTFOLIO_CONST';
import { PortfolioService } from 'src/app/services/portfolio.service';
import { TokenService } from 'src/app/services/token.service';

@Component({
  selector: 'app-experience',
  templateUrl: './experience.component.html',
  styleUrls: ['./experience.component.css']
})
export class ExperienceComponent implements OnInit {

  logged:boolean = false;
  @Input() experienceList: Experience[] = [];
  @Input() portfolio:Portfolio = PORTFOLIO;
  addVisible:boolean = false;
  addTitle:string = "Experience";
  addSection:string = "experience";

  constructor(private portfolioService:PortfolioService,
              private tokenService:TokenService) { }

  ngOnInit(): void {
    if (this.tokenService.isLogged()) this.logged = true;
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
      newItemOrder: event.currentIndex + 1,
      username: this.portfolio.user.username
    }
    this.portfolioService.changeOrderItem(orderData).subscribe();
    moveItemInArray(this.experienceList,event.previousIndex,event.currentIndex);
  }

}
