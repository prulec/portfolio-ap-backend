import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Experience } from 'src/app/model/Experience';
import { PORTFOLIO } from 'src/app/constants/PORTFOLIO_CONST';

@Component({
  selector: 'app-experience-item',
  templateUrl: './experience-item.component.html',
  styleUrls: ['./experience-item.component.css']
})
export class ExperienceItemComponent implements OnInit {

  @Input() experience:Experience = PORTFOLIO.experienceList[0];
  @Output() onDelete:EventEmitter<any> = new EventEmitter();
  deleteVisible:boolean = false;
  editVisible:boolean = false;
  section:string = "experience";
  title:string = "Experience";

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

  deleteItem(experience:Experience){
    this.onDelete.emit(experience);
    this.closeDelete();
  }

}
