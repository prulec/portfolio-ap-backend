import { Component, Input, OnInit } from '@angular/core';
import { Experience } from 'src/app/Experience';

@Component({
  selector: 'app-experience',
  templateUrl: './experience.component.html',
  styleUrls: ['./experience.component.css']
})
export class ExperienceComponent implements OnInit {

  @Input() experienceList: Experience[] = [];

  constructor() { }

  ngOnInit(): void {
  }

}
