import { CdkDragDrop, moveItemInArray } from '@angular/cdk/drag-drop';
import { Component, Input, OnInit } from '@angular/core';
import { Education } from 'src/app/model/Education';
import { OrderData } from 'src/app/model/OrderData';
import { Portfolio } from 'src/app/model/Portfolio';
import { PORTFOLIO } from 'src/app/constants/PORTFOLIO_CONST';
import { PortfolioService } from 'src/app/services/portfolio.service';
import { TokenService } from 'src/app/services/token.service';

@Component({
  selector: 'app-education',
  templateUrl: './education.component.html',
  styleUrls: ['./education.component.css']
})
export class EducationComponent implements OnInit {

  logged:boolean = false;
  @Input() educationList: Education[] = [];
  @Input() portfolio:Portfolio = PORTFOLIO;
  addVisible:boolean = false;
  addTitle:string = "Education";
  addSection:string = "education";

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

  refresh(list:Education[]){
    this.educationList = list;
    console.log("education added!");
  }

  removeItem(item:any){
    this.educationList = this.educationList.filter(i => i.id!=item.id);
  }

  drop (event:CdkDragDrop<Education[]>){
    let orderData: OrderData = {
      id: this.educationList[event.previousIndex].id,
      section: "education",
      newItemOrder: event.currentIndex + 1,
      username: this.portfolio.user.username
    }
    this.portfolioService.changeOrderItem(orderData).subscribe();
    moveItemInArray(this.educationList,event.previousIndex,event.currentIndex);
  }

}
