import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Portfolio } from 'src/app/model/Portfolio';
import { PORTFOLIO } from 'src/app/constants/PORTFOLIO_CONST';
import { Project } from 'src/app/model/Project';
import { TokenService } from 'src/app/services/token.service';

@Component({
  selector: 'app-projects-item',
  templateUrl: './projects-item.component.html',
  styleUrls: ['./projects-item.component.css']
})
export class ProjectsItemComponent implements OnInit {

  projectGalleryVisible: boolean = false;
  @Input() logged:boolean = false;
  @Input() project:Project = PORTFOLIO.projectList[0];
  @Input() portfolio:Portfolio = PORTFOLIO;
  @Output() onDelete:EventEmitter<any> = new EventEmitter();
  @Output() onClick:EventEmitter<any> = new EventEmitter();
  deleteVisible:boolean = false;
  editVisible:boolean = false;
  section:string = "project";
  title:string = "Project";
  emptyPortfolio:Portfolio = PORTFOLIO;

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

  deleteItem(project:Project){
    this.onDelete.emit(project);
    this.closeDelete();
  }

  sendClick(){
    this.onClick.emit();
  }

  closeGallery(): void {
    this.projectGalleryVisible = false;
  }

  openGallery(): void {
    this.projectGalleryVisible = true;
  }

}
