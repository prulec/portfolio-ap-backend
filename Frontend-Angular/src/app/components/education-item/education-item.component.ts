import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Education } from 'src/app/model/Education';
import { PORTFOLIO } from 'src/app/constants/PORTFOLIO_CONST';
import { Portfolio } from 'src/app/model/Portfolio';

@Component({
  selector: 'app-education-item',
  templateUrl: './education-item.component.html',
  styleUrls: ['./education-item.component.css']
})
export class EducationItemComponent implements OnInit {

  @Input() education:Education = PORTFOLIO.educationList[0];
  @Input() portfolio:Portfolio = PORTFOLIO;
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
