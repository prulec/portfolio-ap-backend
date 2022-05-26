import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { PORTFOLIO } from 'src/app/constants/PORTFOLIO_CONST';
import { LoginUser } from 'src/app/model/LoginUser';
import { Portfolio } from 'src/app/model/Portfolio';
import { AuthService } from 'src/app/services/auth.service';
import { TokenService } from 'src/app/services/token.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  @Input() portfolio:Portfolio = PORTFOLIO;
  @Output() onClose:EventEmitter<any> = new EventEmitter();
  @Output() onLogged:EventEmitter<any> = new EventEmitter();
  logged: boolean = false;
  loginFail: boolean = false;
  loginUser: LoginUser = {username:"",password:""};
  username: string = "";
  password: string = "";
  errorMessage: string = "";

  constructor(private tokenService: TokenService,
              private authService:AuthService,
              private router:Router) { }

  ngOnInit(): void {
    if (this.tokenService.getToken()){
      this.logged = true;
      this.loginFail = false;
    }
  }

  onLogin() {
    this.loginUser.username = this.username;
    this.loginUser.password = this.password;
    this.authService.login(this.loginUser).subscribe(
      data => {
        this.logged = true;
        this.loginFail = false;
        this.tokenService.setToken(data.token);
        this.onLogged.emit();
        this.onClose.emit();
        this.router.navigate(['/' + this.portfolio.name + '/edit']);
      },
      err => {
        this.logged = false;
        this.loginFail = true;
        this.errorMessage = err.error.message;
        console.log(this.errorMessage);
      }
    );
  }

  onCancel() {
    this.onClose.emit();
  }

}
