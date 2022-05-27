import { Component, Input, OnInit } from '@angular/core';
import { Portfolio } from 'src/app/model/Portfolio';
import { PORTFOLIO } from 'src/app/constants/PORTFOLIO_CONST';
import { TokenService } from 'src/app/services/token.service';

@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.css']
})
export class AboutComponent implements OnInit {

  logged:boolean = false;
  @Input() portfolio:Portfolio = PORTFOLIO;
  editPhotoUrlVisible:boolean = false;
  editJobTitleVisible:boolean = false;
  editPStatementVisible:boolean = false;
  editFullnameVisible:boolean = false;
  editPhotoUrlTitle:string = "Photo url";
  editJobTitleTitle:string = "Job title";
  editPStatementTitle:string = "Personal statement";
  editFullnameTitle:string = "Fullname";

  /*
  portfolio.photoUrl = https://github.com/prulec/portfolio/raw/main/images/Archive/profle_image_dolltoon%201.png
  portfolio.jobTitle = Título profesional
  portfolio.pstatement = Lorem ipsum dolor sit amet consectetur adipisicing elit. Maiores molestiae fuga provident sapiente quod nobis reiciendis incidunt optio voluptatem iste temporibus porro officia cupiditate unde quibusdam quae vero, qui atque! Lorem, ipsum dolor sit amet consectetur adipisicing elit. Rerum voluptatibus adipisci minima neque ex magnam cupiditate dolore vel! Praesentium facere, officiis distinctio similique obcaecati a impedit deserunt sit architecto totam? Lorem ipsum dolor sit amet consectetur adipisicing elit. Consequuntur ex repellat sunt autem, nostrum dolore, quaerat ea quae recusandae veniam distinctio incidunt? Alias, ipsum minus esse libero accusantium cumque perferendis? Lorem ipsum dolor, sit amet consectetur adipisicing elit. Ipsum, pariatur voluptatem?
  portfolio.user.firstName = Nombre completo
  portfolio.user.lastName = de la persona
  */

  constructor(private tokenService:TokenService) { }

  ngOnInit(): void {
    if (this.tokenService.isLogged()) this.logged = true;
  }

  openEditPopup (target:string): void {
    if (target===this.editPhotoUrlTitle) {
      this.editPhotoUrlVisible = true;
    }
    if (target==this.editJobTitleTitle) {
      this.editJobTitleVisible = true;
    }
    if (target===this.editPStatementTitle) {
      this.editPStatementVisible = true;
    }
    if (target===this.editFullnameTitle) {
      this.editFullnameVisible = true;
    }
  }

  closeEditPopup (target:string) {
    if (target===this.editPhotoUrlTitle) {
      this.editPhotoUrlVisible = false;
    }
    if (target==this.editJobTitleTitle) {
      this.editJobTitleVisible = false;
    }
    if (target===this.editPStatementTitle) {
      this.editPStatementVisible = false;
    }
    if (target===this.editFullnameTitle) {
      this.editFullnameVisible = false;
    }
  }

}
