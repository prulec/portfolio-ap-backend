import { Component, Input, OnInit } from '@angular/core';
import { PORTFOLIO } from 'src/app/PORTFOLIO_CONST';
import { Project } from 'src/app/Project';

@Component({
  selector: 'app-projects-item',
  templateUrl: './projects-item.component.html',
  styleUrls: ['./projects-item.component.css']
})
export class ProjectsItemComponent implements OnInit {

  @Input() project:Project = PORTFOLIO.projectList[0];

  constructor() { }

  ngOnInit(): void {
  }

}
