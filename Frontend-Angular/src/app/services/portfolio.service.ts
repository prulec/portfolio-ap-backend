import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HeaderAboutData } from '../HeaderAboutData';
import { Portfolio } from '../Portfolio';
import { Social } from '../Social';
import { SocialData } from '../SocialData';
import { SocialType } from '../SocialType';

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

  getSocialTypes(): Observable<SocialType[]> {
    return this.http.get<SocialType[]>("http://localhost:8080/socialtypes");
  }

  updateSocialItem(socialData:SocialData): Observable<Social> {
    return this.http.patch<Social>("http://localhost:8080/social/update", socialData, httpOptions);
  }

}
