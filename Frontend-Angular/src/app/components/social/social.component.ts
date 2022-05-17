import { Component, Input, OnInit } from '@angular/core';
import { PORTFOLIO } from 'src/app/PORTFOLIO_CONST';
import { PortfolioService } from 'src/app/services/portfolio.service';
import { Social } from 'src/app/Social';
import { SocialData } from 'src/app/SocialData';
import { SocialType } from 'src/app/SocialType';

@Component({
  selector: 'app-social',
  templateUrl: './social.component.html',
  styleUrls: ['./social.component.css']
})
export class SocialComponent implements OnInit {

  @Input() socialList:Social[] = PORTFOLIO.socialList;
  socialWindowVisible:boolean = false;
  addVisible:boolean = false;
  socialTypes:SocialType[] = [PORTFOLIO.socialList[0].socialTypeData];
  data:SocialData = {url:"", socialTypeName:""};

  /*
  Links íconos redes:
  https://github.com/prulec/portfolio/raw/main/images/Assets/Facebook%20(logo).png
  https://github.com/prulec/portfolio/raw/main/images/Assets/YouTube%20(logo).png
  https://github.com/prulec/portfolio/raw/main/images/Assets/GitHub%20(logo).png
  */

  constructor(private portfolioService:PortfolioService) { }

  ngOnInit(): void {
    this.portfolioService.getSocialTypes().subscribe(
      (data) => {this.socialTypes = data;}
    );
  }

  openWindow () {
    this.socialWindowVisible = true;
  }

  closeWindow () {
    this.socialWindowVisible = false;
  }

  updateItem(social:Social){
    let index = this.socialList.findIndex(s => s.id === social.id);
    this.socialList[index] = social;
  }

  deleteItem(social:Social){
    this.socialList = this.socialList.filter(s => s.id!=social.id);
  }

  openAddForm(){
    this.addVisible = true;
  }

  onSubmit(){
    // VALIDAR
    console.log(this.socialList.length);
    console.log(this.data);
    this.portfolioService.addSocialItem(this.data).subscribe(
      (s) => {
        this.socialList.push(s);
      }
    );
    console.log(this.socialList.length);
    this.data = {url:"", socialTypeName:""};
    this.addVisible = false;
  }

  onDiscard(){
    this.data = {url:"", socialTypeName:""};
    this.addVisible = false;
  }

  goTo (url:string) {
    window.open(url,"_blank");
  }

}
