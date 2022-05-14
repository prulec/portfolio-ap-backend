import { Component, Input, OnInit } from '@angular/core';
import { Education } from 'src/app/Education';
import { PORTFOLIO } from 'src/app/PORTFOLIO_CONST';

@Component({
  selector: 'app-education-item',
  templateUrl: './education-item.component.html',
  styleUrls: ['./education-item.component.css']
})
export class EducationItemComponent implements OnInit {

  @Input() education:Education = PORTFOLIO.educationList[0];

  constructor() { }

  ngOnInit(): void {
  }

}
