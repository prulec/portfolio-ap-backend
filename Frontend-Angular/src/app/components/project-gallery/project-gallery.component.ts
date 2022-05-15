import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { PORTFOLIO } from 'src/app/PORTFOLIO_CONST';
import { Project } from 'src/app/Project';
import { ProjectImage } from 'src/app/ProjectImage';

@Component({
  selector: 'app-project-gallery',
  templateUrl: './project-gallery.component.html',
  styleUrls: ['./project-gallery.component.css']
})
export class ProjectGalleryComponent implements OnInit {

  @Input() project:Project = PORTFOLIO.projectList[0];
  @Input() selectedImage:ProjectImage = this.project.projectImageList[0];
  @Output() onCloseImagesWindow: EventEmitter<any> = new EventEmitter();

  constructor() { }

  ngOnInit(): void {
  }

  onClose(){
    this.onCloseImagesWindow.emit();
  }

  onSelect(image:ProjectImage) {
    this.selectedImage = image;
  }

}
