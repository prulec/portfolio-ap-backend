import { Component, Input, OnInit } from '@angular/core';
import { PORTFOLIO } from 'src/app/PORTFOLIO_CONST';
import { Social } from 'src/app/Social';

@Component({
  selector: 'app-social-item',
  templateUrl: './social-item.component.html',
  styleUrls: ['./social-item.component.css']
})
export class SocialItemComponent implements OnInit {

  @Input() social:Social = PORTFOLIO.socialList[0];

  constructor() { }

  ngOnInit(): void {
  }

}
