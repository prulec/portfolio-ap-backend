import { Component, Input, OnInit } from '@angular/core';
import { PORTFOLIO } from 'src/app/PORTFOLIO_CONST';
import { Social } from 'src/app/Social';

@Component({
  selector: 'app-social',
  templateUrl: './social.component.html',
  styleUrls: ['./social.component.css']
})
export class SocialComponent implements OnInit {

  @Input() socialList:Social[] = PORTFOLIO.socialList;
  socialWindowVisible:boolean = false;

  /*
  Links íconos redes:
  https://github.com/prulec/portfolio/raw/main/images/Assets/Facebook%20(logo).png
  https://github.com/prulec/portfolio/raw/main/images/Assets/YouTube%20(logo).png
  https://github.com/prulec/portfolio/raw/main/images/Assets/GitHub%20(logo).png
  */

  constructor() { }

  ngOnInit(): void {
  }

  openWindow () {
    this.socialWindowVisible = true;
  }

  closeWindow () {
    this.socialWindowVisible = false;
  }

  refresh(social:Social){
    let index = this.socialList.findIndex(s => s.id === social.id);
    this.socialList[index] = social;
  }

}
