import { CdkDragDrop, moveItemInArray } from '@angular/cdk/drag-drop';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { OrderData } from 'src/app/model/OrderData';
import { Portfolio } from 'src/app/model/Portfolio';
import { PORTFOLIO } from 'src/app/constants/PORTFOLIO_CONST';
import { Project } from 'src/app/model/Project';
import { PortfolioService } from 'src/app/services/portfolio.service';

@Component({
  selector: 'app-projects',
  templateUrl: './projects.component.html',
  styleUrls: ['./projects.component.css']
})
export class ProjectsComponent implements OnInit {

  @Input() projectList:Project[] = [];
  @Input() portfolio:Portfolio = PORTFOLIO;
  addVisible:boolean = false;
  addTitle:string = "Project";
  addSection:string = "project";
  oneColumn:boolean = false;

  constructor(private portfolioService:PortfolioService) { }

  ngOnInit(): void {
  }

  openAddPopup (): void {
    this.addVisible = true;
  }

  closeAddPopup () {
    this.addVisible = false;
  }

  refresh(list:Project[]){
    this.projectList = list;
    console.log("project added!");
  }

  removeItem(item:any){
    this.projectList = this.projectList.filter(i => i.id!=item.id);
  }

  toggleOneColumn(){
    this.oneColumn = !this.oneColumn;
  }

  drop (event:CdkDragDrop<Project[]>){
    let orderData: OrderData = {
      id: this.projectList[event.previousIndex].id,
      section: "project",
      newItemOrder: event.currentIndex + 1,
      username: this.portfolio.user.username
    }
    this.portfolioService.changeOrderItem(orderData).subscribe();
    moveItemInArray(this.projectList,event.previousIndex,event.currentIndex);
  }

}
