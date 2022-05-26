import { Component, OnInit } from '@angular/core';
import { PORTFOLIO } from 'src/app/constants/PORTFOLIO_CONST';
import { Portfolio } from 'src/app/model/Portfolio';
import { PortfolioService } from 'src/app/services/portfolio.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-portfolio',
  templateUrl: './portfolio.component.html',
  styleUrls: ['./portfolio.component.css']
})
export class PortfolioComponent implements OnInit {

  portfolio:Portfolio = PORTFOLIO;

  constructor(private portfolioService:PortfolioService,
              private route:Router) { }

  ngOnInit(): void {
    this.portfolioService.getPortfolio(this.route.url.slice(1)).subscribe(data => {
      if (data!=null) this.portfolio = data;
      else console.log("NO DATA!");
    });
  }

}
