import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BACKEND_URL } from '../constants/Constants';
import { JwtDto } from '../model/JwtDto';
import { LoginUser } from '../model/LoginUser';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':'application/json'
  })
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  authURL = BACKEND_URL + "user/"

  constructor(private http:HttpClient) { }

  login(loginUser: LoginUser): Observable<JwtDto> {
    return this.http.post<JwtDto>(this.authURL + "login", loginUser, httpOptions);
  }
}
