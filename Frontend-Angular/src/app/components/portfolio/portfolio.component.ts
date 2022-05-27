import { Component, OnInit } from '@angular/core';
import { PORTFOLIO } from 'src/app/constants/PORTFOLIO_CONST';
import { Portfolio } from 'src/app/model/Portfolio';
import { PortfolioService } from 'src/app/services/portfolio.service';
import { ActivatedRoute, Router } from '@angular/router';
import { TokenService } from 'src/app/services/token.service';

@Component({
  selector: 'app-portfolio',
  templateUrl: './portfolio.component.html',
  styleUrls: ['./portfolio.component.css']
})
export class PortfolioComponent implements OnInit {

  logged:boolean = false;
  portfolioName:string = "";
  portfolio:Portfolio = PORTFOLIO;

  constructor(private portfolioService:PortfolioService,
              private tokenService:TokenService,
              private router:Router,
              private activatedRoute:ActivatedRoute) {
    this.activatedRoute.params.subscribe(
      params => this.portfolioName = params['portfolioName']);
  }

  ngOnInit(): void {
    if (this.tokenService.isLogged()) {
      let username = this.tokenService.getUsername();
      this.portfolioService.getPortfolio(username, this.portfolioName).subscribe(data => {
        if (data!=null) {
          this.portfolio = data;
          this.router.navigate([this.portfolioName + '/edit']);
        } else {
          console.log(this.portfolioName + ": NO DATA!");
          this.router.navigate(['/']);
        }
      });
    } else {
      this.portfolioService.viewPortfolio(this.portfolioName).subscribe(data => {
        if (data!=null) {
          this.portfolio = data;
          this.router.navigate([this.portfolioName]);
        } else console.log(this.portfolioName + ": NO DATA!");
      });
    }
  }

  enableLogged(){
    this.logged = true;
    window.location.reload();
  }

  disableLogged(){
    this.logged = false;
  }

}
