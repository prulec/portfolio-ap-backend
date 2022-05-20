import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { EditUserData } from '../EditUserData';
import { EmailData } from '../EmailData';
import { HeaderAboutData } from '../HeaderAboutData';
import { OrderData } from '../OrderData';
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

  updateHeaderAboutField (portfolio:Portfolio, data:HeaderAboutData): Observable<Portfolio> {
    return this.http.put<Portfolio>(this.portfolioUrl + '/header-about/update', data, httpOptions);
  }

  updateUserData (portfolio:Portfolio, data:EditUserData): Observable<Portfolio> {
    return this.http.patch<Portfolio>(this.portfolioUrl + '/user/update', data, httpOptions);
  }

  getSocialTypes(): Observable<SocialType[]> {
    return this.http.get<SocialType[]>("http://localhost:8080/socialtypes");
  }

  updateSocialItem(socialData:SocialData): Observable<Social> {
    return this.http.patch<Social>("http://localhost:8080/social/update", socialData, httpOptions);
  }

  addSocialItem(socialData:SocialData): Observable<Social> {
    return this.http.post<Social>(this.portfolioUrl + "/social/add", socialData, httpOptions);
  }

  deleteSocialItem(social:Social): Observable<any> {
    return this.http.delete<any>("http://localhost:8080/deleteitem/social/"+social.id)
  }

  changeOrderItem(orderData:OrderData): Observable<string>{
    return this.http.patch("http://localhost:8080/changeorder", orderData, {responseType: 'text'});
  }

  sendContact(emailData:EmailData):Observable<any>{
    return this.http.post("http://localhost:8080/sendcontact",emailData,httpOptions);
  }

  addItem(item:any, section:string): Observable<any> {
    return this.http.post<any>(this.portfolioUrl + "/" + section + "/add", item, httpOptions);
  }

  deleteItem(item:any, section:string): Observable<any> {
    return this.http.delete("http://localhost:8080/deleteitem/" + section + "/" + item.id);
  }

  updateItem(data:any, section:string): Observable<any> {
    return this.http.patch<any>("http://localhost:8080/" + section + "/update", data, httpOptions);
  }

}
