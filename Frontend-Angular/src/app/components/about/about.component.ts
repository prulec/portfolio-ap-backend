import { Component, Input, OnInit } from '@angular/core';
import { Portfolio } from 'src/app/Portfolio';
import { PORTFOLIO } from 'src/app/PORTFOLIO_CONST';

@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.css']
})
export class AboutComponent implements OnInit {

  @Input() portfolio:Portfolio = PORTFOLIO;

  /*
  portfolio.photoUrl = https://github.com/prulec/portfolio/raw/main/images/Archive/profle_image_dolltoon%201.png
  portfolio.jobTitle = Título profesional
  portfolio.pstatement = Lorem ipsum dolor sit amet consectetur adipisicing elit. Maiores molestiae fuga provident sapiente quod nobis reiciendis incidunt optio voluptatem iste temporibus porro officia cupiditate unde quibusdam quae vero, qui atque! Lorem, ipsum dolor sit amet consectetur adipisicing elit. Rerum voluptatibus adipisci minima neque ex magnam cupiditate dolore vel! Praesentium facere, officiis distinctio similique obcaecati a impedit deserunt sit architecto totam? Lorem ipsum dolor sit amet consectetur adipisicing elit. Consequuntur ex repellat sunt autem, nostrum dolore, quaerat ea quae recusandae veniam distinctio incidunt? Alias, ipsum minus esse libero accusantium cumque perferendis? Lorem ipsum dolor, sit amet consectetur adipisicing elit. Ipsum, pariatur voluptatem?
  portfolio.user.firstName = Nombre completo
  portfolio.user.lastName = de la persona
  */

  constructor() { }

  ngOnInit(): void {
  }

}
