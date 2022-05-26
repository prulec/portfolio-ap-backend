import { Component, Input, OnInit } from '@angular/core';
import { Portfolio } from 'src/app/model/Portfolio';
import { PORTFOLIO } from 'src/app/constants/PORTFOLIO_CONST';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent implements OnInit {

  @Input() portfolio:Portfolio = PORTFOLIO;
  contactFormVisible:boolean = false;
  editVisible: boolean = false;
  editTitle: string = "Email";

  constructor() { }

  ngOnInit(): void {
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
