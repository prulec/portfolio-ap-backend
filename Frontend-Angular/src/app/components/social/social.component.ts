import { CdkDragDrop, moveItemInArray } from '@angular/cdk/drag-drop';
import { Component, Input, OnInit } from '@angular/core';
import { OrderData } from 'src/app/model/OrderData';
import { Portfolio } from 'src/app/model/Portfolio';
import { PORTFOLIO } from 'src/app/constants/PORTFOLIO_CONST';
import { PortfolioService } from 'src/app/services/portfolio.service';
import { Social } from 'src/app/model/Social';
import { SocialData } from 'src/app/model/SocialData';
import { SocialType } from 'src/app/model/SocialType';

@Component({
  selector: 'app-social',
  templateUrl: './social.component.html',
  styleUrls: ['./social.component.css']
})
export class SocialComponent implements OnInit {

  @Input() socialList:Social[] = PORTFOLIO.socialList;
  @Input() portfolio:Portfolio = PORTFOLIO;
  socialWindowVisible:boolean = false;
  addVisible:boolean = false;
  socialTypes:SocialType[] = [PORTFOLIO.socialList[0].socialTypeData];
  data:SocialData = {url:"", socialTypeName:"", username:"", portfolioName:""};

  /*
  Links íconos redes:
  https://github.com/prulec/portfolio/raw/main/images/Assets/Facebook%20(logo).png
  https://github.com/prulec/portfolio/raw/main/images/Assets/YouTube%20(logo).png
  https://github.com/prulec/portfolio/raw/main/images/Assets/GitHub%20(logo).png
  */

  constructor(private portfolioService:PortfolioService) { }

  ngOnInit(): void {}

  getSocialTypes(){
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

  openAddForm() {
    this.getSocialTypes();
    this.addVisible = true;
  }

  onSubmit(){
    // VALIDAR
    this.data.username = this.portfolio.user.username;
    this.data.portfolioName = this.portfolio.name;
    this.portfolioService.addItem(this.data, "social").subscribe(
      (s) => {
        this.socialList.push(s);
      }
    );
    console.log(this.socialList.length);
    this.data = {url:"", socialTypeName:"", username:"", portfolioName:""};
    this.addVisible = false;
  }

  onDiscard(){
    this.data = {url:"", socialTypeName:"", username:"", portfolioName:""};
    this.addVisible = false;
  }

  goTo (url:string) {
    window.open(url,"_blank");
  }

  drop (event:CdkDragDrop<Social[]>){
    let orderData: OrderData = {
      id: this.socialList[event.previousIndex].id,
      section: "social",
      newItemOrder: event.currentIndex + 1,
      username: this.portfolio.user.username
    }
    this.portfolioService.changeOrderItem(orderData).subscribe();
    moveItemInArray(this.socialList,event.previousIndex,event.currentIndex);
  }

}
