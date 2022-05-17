import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Portfolio } from 'src/app/Portfolio';
import { PORTFOLIO } from 'src/app/PORTFOLIO_CONST';
import { PortfolioService } from 'src/app/services/portfolio.service';
import { Social } from 'src/app/Social';
import { SocialData } from 'src/app/SocialData';
import { SocialType } from 'src/app/SocialType';

@Component({
  selector: 'app-social-item',
  templateUrl: './social-item.component.html',
  styleUrls: ['./social-item.component.css']
})
export class SocialItemComponent implements OnInit {

  editVisible:boolean = false;
  socialTypes:SocialType[] = [PORTFOLIO.socialList[0].socialTypeData];
  @Input() social:Social = PORTFOLIO.socialList[0];
  @Output() onUpdate:EventEmitter<Social> = new EventEmitter();
  socialTypeName:string = "";
  socialUrl:string = "";
  data:SocialData = {id:BigInt(0), url:"", socialTypeName:""};

  constructor(private portfolioService:PortfolioService) { }

  ngOnInit(): void {
    this.portfolioService.getSocialTypes().subscribe(
      (data) => {this.socialTypes = data;}
    );
  }

  openEditForm(){
    this.socialTypeName = this.social.socialTypeData.name;
    this.socialUrl = this.social.url;
    this.editVisible = true;
  }

  onSubmit(){
    // VALIDAR
    this.data.id = this.social.id || BigInt(0);
    this.data.url = this.socialUrl;
    this.data.socialTypeName = this.socialTypeName;
    this.portfolioService.updateSocialItem(this.data).subscribe(
      (s) => {
        this.social = s;
        this.onUpdate.emit(this.social);
      }
    );
    this.data = {id:BigInt(0), url:"", socialTypeName:""};
    this.editVisible = false;
  }

  onDiscard(){
    this.data = {id:BigInt(0), url:"", socialTypeName:""};
    this.editVisible = false;
  }

}
