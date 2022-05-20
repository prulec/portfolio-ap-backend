import { Component, Input, OnInit } from '@angular/core';
import { Experience } from 'src/app/Experience';

@Component({
  selector: 'app-experience',
  templateUrl: './experience.component.html',
  styleUrls: ['./experience.component.css']
})
export class ExperienceComponent implements OnInit {

  @Input() experienceList: Experience[] = [];
  addVisible:boolean = false;
  addTitle:string = "Experience";
  addSection:string = "experience";

  constructor() { }

  ngOnInit(): void {
  }

  openAddPopup (): void {
    this.addVisible = true;
  }

  closeAddPopup () {
    this.addVisible = false;
  }

  refresh(list:Experience[]){
    this.experienceList = list;
    console.log("experience added!");
  }

}
