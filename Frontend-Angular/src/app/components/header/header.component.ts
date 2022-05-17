import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Portfolio } from 'src/app/Portfolio';
import { PORTFOLIO } from 'src/app/PORTFOLIO_CONST';
import { PortfolioService } from 'src/app/services/portfolio.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  editVisible:boolean = false;
  editTitle:string = "Banner url";
  //socialVisible:boolean = false;

  @Input() portfolio:Portfolio = PORTFOLIO;
  
  /*
  portfolio.bannerUrl = https://github.com/prulec/portfolio/raw/main/images/Archive/astronaut3.png
  */

  constructor(private portfolioService:PortfolioService) { }

  ngOnInit(): void {
  }

  togglePortfolioVisibility() {
    this.portfolioService.toggleVisibility().subscribe(
      (p) => { this.portfolio.visible = p.visible;}
    );
  }

  openEditPopup (): void {
    this.editVisible = true;
  }

  closeEditPopup () {
    this.editVisible = false;
  }

}
