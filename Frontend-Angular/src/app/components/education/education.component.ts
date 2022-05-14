import { Component, Input, OnInit } from '@angular/core';
import { Education } from 'src/app/Education';

@Component({
  selector: 'app-education',
  templateUrl: './education.component.html',
  styleUrls: ['./education.component.css']
})
export class EducationComponent implements OnInit {

  @Input() educationList: Education[] = [];

  constructor() { }

  ngOnInit(): void {
  }

}
