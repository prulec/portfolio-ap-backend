import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { HeaderAboutData } from 'src/app/HeaderAboutData';
import { Portfolio } from 'src/app/Portfolio';
import { PORTFOLIO } from 'src/app/PORTFOLIO_CONST';
import { PortfolioService } from 'src/app/services/portfolio.service';

@Component({
  selector: 'app-edit-item',
  templateUrl: './edit-item.component.html',
  styleUrls: ['./edit-item.component.css']
})
export class EditItemComponent implements OnInit {

  banner_url_string:string = "banner_url";
  photo_url_string:string = "photo_url";
  job_title_string:string = "job_title";
  p_statement_string:string = "p_statement";

  @Input() title:string = "";
  @Input() portfolio:Portfolio = PORTFOLIO;
  @Output() onSave:EventEmitter<any> = new EventEmitter();
  data:HeaderAboutData = {field:"", value:""};

  constructor(private portfolioService:PortfolioService) { }

  ngOnInit(): void {
  }

  onSubmit() {
    //VALIDAR
    switch (this.title) {
      case "Banner url":
        this.data.field = this.banner_url_string;
        this.data.value = this.portfolio.bannerUrl;
        this.portfolioService.updateHeaderAboutField(this.portfolio,this.data).subscribe();
        break;    
      default:
        break;
    }
    this.onSave.emit();
  }

}
