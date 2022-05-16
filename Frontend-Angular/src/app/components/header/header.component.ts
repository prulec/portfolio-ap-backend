import { Component, OnInit, Input } from '@angular/core';
import { Portfolio } from 'src/app/Portfolio';
import { PORTFOLIO } from 'src/app/PORTFOLIO_CONST';
import { PortfolioService } from 'src/app/services/portfolio.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  banner_url_string:string = "banner_url";
  photo_url_string:string = "photo_url";
  job_title_string:string = "job_title";
  p_statement_string:string = "p_statement";

  editVisible:boolean = false;
  editTitle:string = "";
  @Input() portfolio:Portfolio = PORTFOLIO;
  
  /*
  portfolio.bannerUrl = https://github.com/prulec/portfolio/raw/main/images/Archive/astronaut3.png
  Links íconos redes:
  https://github.com/prulec/portfolio/raw/main/images/Assets/Facebook%20(logo).png
  https://github.com/prulec/portfolio/raw/main/images/Assets/YouTube%20(logo).png
  https://github.com/prulec/portfolio/raw/main/images/Assets/GitHub%20(logo).png
  */

  constructor(private portfolioService:PortfolioService) { }

  ngOnInit(): void {
  }

  togglePortfolioVisibility() {
    this.portfolioService.toggleVisibility().subscribe(
      (p) => { this.portfolio.visible = p.visible;}
    );
  }

  openEditPopup (field:string): void {
    switch (field) {
      case "banner_url":
        this.editTitle = "Banner url";
        break;
      case "photo_url":
        this.editTitle = "Photo url";
        break;
      case "job_title":
        this.editTitle = "Job title";
        break;
      case "p_statement":
        this.editTitle = "Personal statement";
        break;    
      default:
        this.editTitle = "";
    }    
    this.editVisible = true;
  }

  closeEditPopup () {
    this.editVisible = false;
  }

}
