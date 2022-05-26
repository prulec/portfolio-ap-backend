import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Portfolio } from 'src/app/model/Portfolio';
import { PORTFOLIO } from 'src/app/constants/PORTFOLIO_CONST';
import { PortfolioService } from 'src/app/services/portfolio.service';
import { TokenService } from 'src/app/services/token.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  @Output() onLogged:EventEmitter<any> = new EventEmitter();
  @Output() onLoggedOut:EventEmitter<any> = new EventEmitter();
  logged: boolean = false;
  loginVisible: boolean = false;
  editVisible:boolean = false;
  editTitle:string = "Banner url";
  //socialVisible:boolean = false;

  @Input() portfolio:Portfolio = PORTFOLIO;
  
  /*
  portfolio.bannerUrl = https://github.com/prulec/portfolio/raw/main/images/Archive/astronaut3.png
  */

  constructor(private portfolioService:PortfolioService,
              private tokenService:TokenService,
              private router:Router) { }

  ngOnInit(): void {
  }

  togglePortfolioVisibility() {
    this.portfolioService.toggleVisibility(this.portfolio).subscribe(
      (p) => { this.portfolio.visible = p.visible;}
    );
  }

  openEditPopup (): void {
    this.editVisible = true;
  }

  closeEditPopup () {
    this.editVisible = false;
  }

  closeLoginPopup() {
    this.loginVisible = false;
  }

  openLoginPopup() {
    this.loginVisible = true;
  }

  logout(){
    this.tokenService.logout();
    this.logged = false;
    this.onLoggedOut.emit();
    this.router.navigate(['/' + this.portfolio.name]);
  }

  enableLogged() {
    this.logged = true;
    this.onLogged.emit();
  }

}
