import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Education } from 'src/app/Education';
import { PORTFOLIO } from 'src/app/PORTFOLIO_CONST';

@Component({
  selector: 'app-education-item',
  templateUrl: './education-item.component.html',
  styleUrls: ['./education-item.component.css']
})
export class EducationItemComponent implements OnInit {

  @Input() education:Education = PORTFOLIO.educationList[0];
  @Output() onDelete:EventEmitter<any> = new EventEmitter();
  deleteVisible:boolean = false;
  editVisible:boolean = false;
  section:string = "education";
  title:string = "Education";

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

  deleteItem(education:Education){
    this.onDelete.emit(education);
    this.closeDelete();
  }

}
