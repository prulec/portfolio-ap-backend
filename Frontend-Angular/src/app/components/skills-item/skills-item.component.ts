import { Component, Input, OnInit } from '@angular/core';
import { PORTFOLIO } from 'src/app/PORTFOLIO_CONST';
import { Skill } from 'src/app/Skill';

@Component({
  selector: 'app-skills-item',
  templateUrl: './skills-item.component.html',
  styleUrls: ['./skills-item.component.css']
})
export class SkillsItemComponent implements OnInit {

  @Input() skill:Skill = PORTFOLIO.skillList[0];

  constructor() { }

  ngOnInit(): void {
  }

}
