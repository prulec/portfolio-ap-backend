import { CdkDragDrop, moveItemInArray } from '@angular/cdk/drag-drop';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { OrderData } from 'src/app/model/OrderData';
import { PORTFOLIO } from 'src/app/constants/PORTFOLIO_CONST';
import { Project } from 'src/app/model/Project';
import { ProjectImage } from 'src/app/model/ProjectImage';
import { PortfolioService } from 'src/app/services/portfolio.service';
import { Portfolio } from 'src/app/model/Portfolio';
import { TokenService } from 'src/app/services/token.service';

@Component({
  selector: 'app-project-gallery',
  templateUrl: './project-gallery.component.html',
  styleUrls: ['./project-gallery.component.css']
})
export class ProjectGalleryComponent implements OnInit {

  @Input() logged:boolean = false;
  @Input() project:Project = PORTFOLIO.projectList[0];
  @Input() portfolio:Portfolio = PORTFOLIO;
  @Input() selectedImage:ProjectImage = PORTFOLIO.projectList[0].projectImageList[0];
  @Output() onDelete:EventEmitter<any> = new EventEmitter();
  @Output() onCloseImagesWindow: EventEmitter<any> = new EventEmitter();
  changeOrderActivated:boolean = false;
  addVisible:boolean = false;
  addTitle:string = "Project image";
  addSection:string = "image";
  deleteVisible:boolean = false;
  editVisible:boolean = false;
  section:string = "projectimage";
  title:string = "Project image";

  constructor(private portfolioService:PortfolioService) { }

  ngOnInit(): void {}

  onClose(){
    this.onCloseImagesWindow.emit();
  }

  onSelect(image:ProjectImage) {
    this.selectedImage = image;
  }

  openAddPopup (): void {
    this.addVisible = true;
  }

  closeAddPopup () {
    this.addVisible = false;
  }

  refresh(list:ProjectImage[]){
    this.project.projectImageList = list;
    console.log("image added!");
  }

  removeItem(item:any){
    this.project.projectImageList = this.project.projectImageList.filter(i => i.id!=item.id);
  }

  drop (event:CdkDragDrop<ProjectImage[]>){
    let orderData: OrderData = {
      id: this.project.projectImageList[event.previousIndex].id,
      section: "projectimage",
      newItemOrder: event.currentIndex + 1,
      username: this.portfolio.user.username
    }
    this.portfolioService.changeOrderItem(orderData).subscribe();
    moveItemInArray(this.project.projectImageList,event.previousIndex,event.currentIndex);
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

  deleteItem(image:ProjectImage){
    this.removeItem(image);
    this.closeDelete();
  }

  toggleChangeOrder(){
    this.changeOrderActivated = !this.changeOrderActivated;
  }

}
