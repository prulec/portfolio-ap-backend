import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HeaderAboutData } from '../HeaderAboutData';
import { Portfolio } from '../Portfolio';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':'application/json'
  })
}

@Injectable({
  providedIn: 'root'
})
export class PortfolioService {

  portfolioUrl:string = "http://localhost:8080/portfolio2";

  constructor(private http:HttpClient) { }

  getPortfolio(): Observable<Portfolio> {
    return this.http.get<Portfolio>(this.portfolioUrl + '/edit');
  }

  toggleVisibility(): Observable<Portfolio>{
    return this.http.patch<Portfolio>(this.portfolioUrl + '/visibility', {}, httpOptions);
  }

  updateHeaderAboutField (portfolio:Portfolio, data:HeaderAboutData) {
    return this.http.put<Portfolio>(this.portfolioUrl + '/header-about/update', data, httpOptions);
  }

}
