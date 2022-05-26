import { Component, OnInit } from '@angular/core';
import { PORTFOLIO } from 'src/app/constants/PORTFOLIO_CONST';
import { Portfolio } from 'src/app/model/Portfolio';
import { PortfolioService } from 'src/app/services/portfolio.service';
import { Router } from '@angular/router';
import { TokenService } from 'src/app/services/token.service';

@Component({
  selector: 'app-portfolio',
  templateUrl: './portfolio.component.html',
  styleUrls: ['./portfolio.component.css']
})
export class PortfolioComponent implements OnInit {

  logged:boolean = false;
  portfolio:Portfolio = PORTFOLIO;

  constructor(private portfolioService:PortfolioService,
              private tokenService:TokenService,
              private router:Router) { }

  ngOnInit(): void {
    let portfolioName = this.router.url.slice(1).split("/")[0];
    if (this.tokenService.isLogged()) {
      let username = this.tokenService.getUsername();
      this.portfolioService.getPortfolio(username, portfolioName).subscribe(data => {
        if (data!=null) {
          this.portfolio = data;
          this.router.navigate([portfolioName + '/edit']);
        } else console.log("NO DATA!");
      });
    } else {
      this.portfolioService.viewPortfolio(this.router.url.slice(1)).subscribe(data => {
        if (data!=null) this.portfolio = data;
        else console.log("NO DATA!");
      });
    }
  }

  enableLogged(){
    this.logged = true;
  }

  disableLogged(){
    this.logged = false;
  }

}
