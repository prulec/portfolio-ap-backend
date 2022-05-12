import { Component, OnInit } from '@angular/core';
import { PORTFOLIO } from 'src/app/PORTFOLIO_CONST';
import { Portfolio } from 'src/app/Portfolio';
import { PortfolioService } from 'src/app/services/portfolio.service';
import { SOCIAL } from 'src/app/SOCIAL_CONST';

@Component({
  selector: 'app-portfolio',
  templateUrl: './portfolio.component.html',
  styleUrls: ['./portfolio.component.css']
})
export class PortfolioComponent implements OnInit {

  portfolio:Portfolio = PORTFOLIO;

  constructor(private portfolioService:PortfolioService) { }

  ngOnInit(): void {
    this.portfolioService.getPortfolio().subscribe(data => {
      this.portfolio = data;
      while (this.portfolio.socialList.length<3) {
        this.portfolio.socialList.push(SOCIAL);
      }
    });
  }

}
