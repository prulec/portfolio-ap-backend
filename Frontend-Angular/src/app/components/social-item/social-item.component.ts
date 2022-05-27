import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Portfolio } from 'src/app/model/Portfolio';
import { PORTFOLIO } from 'src/app/constants/PORTFOLIO_CONST';
import { PortfolioService } from 'src/app/services/portfolio.service';
import { Social } from 'src/app/model/Social';
import { SocialData } from 'src/app/model/SocialData';
import { SocialType } from 'src/app/model/SocialType';

@Component({
  selector: 'app-social-item',
  templateUrl: './social-item.component.html',
  styleUrls: ['./social-item.component.css']
})
export class SocialItemComponent implements OnInit {

  editVisible:boolean = false;
  deleteVisible:boolean = false;
  socialTypes:SocialType[] = [PORTFOLIO.socialList[0].socialTypeData];
  @Input() social:Social = PORTFOLIO.socialList[0];
  @Input() portfolio:Portfolio = PORTFOLIO;
  @Output() onUpdate:EventEmitter<Social> = new EventEmitter();
  @Output() onDelete:EventEmitter<Social> = new EventEmitter();
  data:SocialData = {id:BigInt(0), url:"", socialTypeName:"", username:"", portfolioName:""};
  edition:string = "edit";
  deletion:string = "delete";

  constructor(private portfolioService:PortfolioService) { }

  ngOnInit(): void {}
  
  getSocialTypes(){
    this.portfolioService.getSocialTypes().subscribe(
      (data) => {this.socialTypes = data;}
    );
  }

  openEditForm(){
    this.getSocialTypes();
    this.data.portfolioName = this.portfolio.name;
    this.data.username = this.portfolio.user.username;
    this.data.socialTypeName = this.social.socialTypeData.name;
    this.data.url = this.social.url;
    this.data.id = this.social.id;
    this.editVisible = true;
  }

  onSubmit(operation:string){
    if (operation===this.edition) {
      // VALIDAR
      this.portfolioService.updateItem(this.data, "social").subscribe(
        (s) => {
          this.social = s;
          this.onUpdate.emit(this.social);
        }
      );
      this.data = {id:BigInt(0), url:"", socialTypeName:"", username:"", portfolioName:""};
      this.editVisible = false;
    }
    if (operation===this.deletion) {
      this.portfolioService.deleteItem(this.social.id, "social", this.portfolio.user.username).subscribe();
      this.onDelete.emit(this.social);
    }
  }

  onDiscard(){
    this.data = {id:BigInt(0), url:"", socialTypeName:"", username:"", portfolioName:""};
    this.editVisible = false;
  }

  openDelete() {
    this.deleteVisible = true;
  }

  cancelDelete(){
    this.deleteVisible = false;
  }

}
