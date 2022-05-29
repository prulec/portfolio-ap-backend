import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { PORTFOLIO } from 'src/app/constants/PORTFOLIO_CONST';
import { LoginUser } from 'src/app/model/LoginUser';
import { Portfolio } from 'src/app/model/Portfolio';
import { User } from 'src/app/model/User';
import { AuthService } from 'src/app/services/auth.service';
import { PortfolioService } from 'src/app/services/portfolio.service';
import { TokenService } from 'src/app/services/token.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  @Input() portfolio:Portfolio = PORTFOLIO;
  @Input() isChild:boolean = false;
  @Output() onClose:EventEmitter<any> = new EventEmitter();
  @Output() onLogged:EventEmitter<any> = new EventEmitter();
  logged: boolean = false;
  loginFail: boolean = false;
  loginUser: LoginUser = {username:"",password:""};
  username: string = "";
  password: string = "";
  errorMessage: string = "";
  loggedUser:User = {id:BigInt(0),username:"",password:"",firstName:"",lastName:"",email:"",portfolioList:[]};
  portfolioList:Portfolio[] = [PORTFOLIO];

  constructor(private tokenService: TokenService,
              private authService:AuthService,
              private portfolioService:PortfolioService,
              private router:Router) { }

  ngOnInit(): void {
    if (this.tokenService.getToken()){
      this.logged = true;
      this.loginFail = false;
      this.tokenService.getUsername();
      this.portfolioService.getUser(this.tokenService.getUsername()).subscribe(
        u => {
          this.loggedUser = u;
          this.portfolioList = u.portfolioList;
        }
      );
    }
  }

  onLogin() {
    this.loginUser.username = this.username;
    this.loginUser.password = this.password;
    this.authService.login(this.loginUser).subscribe({
      next: (data) => {
        this.logged = true;
        this.loginFail = false;
        this.tokenService.setToken(data.token);
        if(this.isChild){
          this.onLogged.emit();
        } else {
          this.router.navigate(['/']);
          this.tokenService.getUsername();
          this.portfolioService.getUser(this.tokenService.getUsername()).subscribe(
            u => {
              this.loggedUser = u;
              this.portfolioList = u.portfolioList;
            }
          );
        }
      },
      error: (e) => {
        this.logged = false;
        this.loginFail = true;
        if (e.status === 401) this.errorMessage = "Incorrect credentials...";
        else console.log(e);
      }
    });
  }

  onCancel() {
    this.onClose.emit();
  }

  logout(){
    this.tokenService.logout();
    this.logged = false;
    this.username = "";
    this.password = "";
    this.router.navigate(['/']);
  }

  goTo(name:string){
    this.router.navigate(['/' + name]);
    //window.open('/' + name, '_blank');
  }

  disableFail() {
    this.loginFail = false;
  }

}
