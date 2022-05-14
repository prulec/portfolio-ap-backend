import { Component, Input, OnInit } from '@angular/core';
import { Skill } from 'src/app/Skill';

@Component({
  selector: 'app-skills',
  templateUrl: './skills.component.html',
  styleUrls: ['./skills.component.css']
})
export class SkillsComponent implements OnInit {

  @Input() skillList:Skill[] = []; 

  constructor() { }

  ngOnInit(): void {
  }

}
