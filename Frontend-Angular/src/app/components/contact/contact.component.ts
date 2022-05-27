import { Component, Input, OnInit } from '@angular/core';
import { Portfolio } from 'src/app/model/Portfolio';
import { PORTFOLIO } from 'src/app/constants/PORTFOLIO_CONST';
import { TokenService } from 'src/app/services/token.service';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent implements OnInit {

  logged:boolean = false;
  @Input() portfolio:Portfolio = PORTFOLIO;
  contactFormVisible:boolean = false;
  editVisible: boolean = false;
  editTitle: string = "Email";

  constructor(private tokenService:TokenService) { }

  ngOnInit(): void {
    if (this.tokenService.isLogged()) this.logged = true;
  }

  openForm(){
    this.contactFormVisible = true;
  }

  closeForm () {
    this.contactFormVisible = false;
  }

  openEditPopup(){
    this.editVisible = true;
  }

  closeEditPopup () {
    this.editVisible = false;
  }

}
