import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { PORTFOLIO } from 'src/app/PORTFOLIO_CONST';
import { Project } from 'src/app/Project';

@Component({
  selector: 'app-project-gallery',
  templateUrl: './project-gallery.component.html',
  styleUrls: ['./project-gallery.component.css']
})
export class ProjectGalleryComponent implements OnInit {

  @Input() project:Project = PORTFOLIO.projectList[0];
  @Output() onCloseImagesWindow: EventEmitter<any> = new EventEmitter();

  constructor() { }

  ngOnInit(): void {
  }

  onClose(){
    this.onCloseImagesWindow.emit();
  }

}
