import { Component, Output, EventEmitter, OnInit, Input } from '@angular/core';
import { EmailData } from 'src/app/EmailData';
import { PortfolioService } from 'src/app/services/portfolio.service';

@Component({
  selector: 'app-contact-form',
  templateUrl: './contact-form.component.html',
  styleUrls: ['./contact-form.component.css']
})
export class ContactFormComponent implements OnInit {

  @Input() destination:string = "";
  data:EmailData = {replyTo:"",subject:"",body:"",destination:""};
  @Output() onClose:EventEmitter<any> = new EventEmitter();

  constructor(private portfolioService:PortfolioService) { }

  ngOnInit(): void {
  }

  onSubmit() {
    this.data.destination = this.destination;
    this.portfolioService.sendContact(this.data).subscribe();
    this.data = {replyTo:"",subject:"",body:"",destination:""};
    this.onClose.emit();
  }

  onDiscard() {
    this.onClose.emit();
  }

}
