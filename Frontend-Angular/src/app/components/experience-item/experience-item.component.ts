import { Component, Input, OnInit } from '@angular/core';
import { Experience } from 'src/app/Experience';
import { PORTFOLIO } from 'src/app/PORTFOLIO_CONST';

@Component({
  selector: 'app-experience-item',
  templateUrl: './experience-item.component.html',
  styleUrls: ['./experience-item.component.css']
})
export class ExperienceItemComponent implements OnInit {

  @Input() experience:Experience = PORTFOLIO.experienceList[0];

  constructor() { }

  ngOnInit(): void {
  }

}
