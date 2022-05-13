import { Component, Input, OnInit } from '@angular/core';
import { Experience } from 'src/app/Experience';
import { EXPERIENCE } from 'src/app/EXPERIENCE_CONST';

@Component({
  selector: 'app-experience-item',
  templateUrl: './experience-item.component.html',
  styleUrls: ['./experience-item.component.css']
})
export class ExperienceItemComponent implements OnInit {

  @Input() experience:Experience = EXPERIENCE;

  constructor() { }

  ngOnInit(): void {
  }

}
