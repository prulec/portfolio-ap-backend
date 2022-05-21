import { CdkDragDrop, moveItemInArray } from '@angular/cdk/drag-drop';
import { Component, Input, OnInit } from '@angular/core';
import { OrderData } from 'src/app/OrderData';
import { PortfolioService } from 'src/app/services/portfolio.service';
import { Skill } from 'src/app/Skill';

@Component({
  selector: 'app-skills',
  templateUrl: './skills.component.html',
  styleUrls: ['./skills.component.css']
})
export class SkillsComponent implements OnInit {

  @Input() skillList:Skill[] = [];
  addVisible:boolean = false;
  addTitle:string = "Skill";
  addSection:string = "skill";
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

  refresh(list:Skill[]){
    this.skillList = list;
    console.log("skill added!");
  }

  removeItem(item:any){
    this.skillList = this.skillList.filter(i => i.id!=item.id);
  }

  toggleOneColumn(){
    this.oneColumn = !this.oneColumn;
  }

  drop (event:CdkDragDrop<Skill[]>){
    let orderData: OrderData = {
      id: this.skillList[event.previousIndex].id,
      section: "skill",
      newItemOrder: event.currentIndex + 1
    }
    this.portfolioService.changeOrderItem(orderData).subscribe();
    moveItemInArray(this.skillList,event.previousIndex,event.currentIndex);
  }

}
