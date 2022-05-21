import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { PORTFOLIO } from 'src/app/PORTFOLIO_CONST';
import { Skill } from 'src/app/Skill';

@Component({
  selector: 'app-skills-item',
  templateUrl: './skills-item.component.html',
  styleUrls: ['./skills-item.component.css']
})
export class SkillsItemComponent implements OnInit {

  @Input() skill:Skill = PORTFOLIO.skillList[0];
  @Output() onDelete:EventEmitter<any> = new EventEmitter();
  @Output() onClick:EventEmitter<any> = new EventEmitter();
  deleteVisible:boolean = false;
  editVisible:boolean = false;
  section:string = "skill";
  title:string = "Skill";

  constructor() { }

  ngOnInit(): void {
  }

  openEdit(){
    this.editVisible = true;
  }

  closeEdit(){
    this.editVisible = false;
  }

  openDelete(){
    this.deleteVisible = true;
  }

  closeDelete(){
    this.deleteVisible = false;
  }

  deleteItem(skill:Skill){
    this.onDelete.emit(skill);
    this.closeDelete();
  }

  sendClick(){
    this.onClick.emit();
  }

}
