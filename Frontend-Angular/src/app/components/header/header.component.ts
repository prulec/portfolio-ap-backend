import { Component, OnInit, Input } from '@angular/core';
import { PORTFOLIO } from 'src/app/PORTFOLIO_CONST';
import { Social } from 'src/app/Social';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  @Input() username:string = "";
  @Input() visible:boolean = false;
  @Input() bannerUrl:string = "";  // https://github.com/prulec/portfolio/raw/main/images/Archive/astronaut3.png
  @Input() socialList: Social[] = PORTFOLIO.socialList;
  
  /*
  Links íconos redes:
  https://github.com/prulec/portfolio/raw/main/images/Assets/Facebook%20(logo).png
  https://github.com/prulec/portfolio/raw/main/images/Assets/YouTube%20(logo).png
  https://github.com/prulec/portfolio/raw/main/images/Assets/GitHub%20(logo).png
  */

  constructor() { }

  ngOnInit(): void {
  }

}
