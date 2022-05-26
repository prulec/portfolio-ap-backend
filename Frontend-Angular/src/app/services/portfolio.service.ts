import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { EditUserData } from '../model/EditUserData';
import { EmailData } from '../model/EmailData';
import { HeaderAboutData } from '../model/HeaderAboutData';
import { OrderData } from '../model/OrderData';
import { Portfolio } from '../model/Portfolio';
import { Project } from '../model/Project';
import { ProjectImage } from '../model/ProjectImage';
import { ProjectImageData } from '../model/ProjectImageData';
import { Social } from '../model/Social';
import { SocialData } from '../model/SocialData';
import { SocialType } from '../model/SocialType';
import { UserData } from '../model/UserData';
import { VisibilityData } from '../model/VisibilityData';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':'application/json'
  })
}

@Injectable({
  providedIn: 'root'
})
export class PortfolioService {

  //portfolioUrl:string = "http://localhost:8080/portfolio2";
  baseUrl:string = "http://192.168.1.79:8080/"

  constructor(private http:HttpClient) { }

  viewPortfolio(portfolioName:string): Observable<Portfolio> {
    return this.http.get<Portfolio>(this.baseUrl + "portfolio/view/" + portfolioName);
  }

  getPortfolio(username:string, portfolioName:string): Observable<Portfolio> {
    return this.http.get<Portfolio>(this.baseUrl + "portfolio/edit/" + username + "/" + portfolioName);
  }

  toggleVisibility(data:VisibilityData): Observable<Portfolio>{
    return this.http.patch<Portfolio>(this.baseUrl + 'portfolio/visibility', data, httpOptions);
  }

  updateHeaderAboutField (data:HeaderAboutData): Observable<Portfolio> {
    return this.http.put<Portfolio>(this.baseUrl + 'portfolio/header-about/update', data, httpOptions);
  }

  updateUserData (data:UserData): Observable<Portfolio> {
    return this.http.patch<Portfolio>(this.baseUrl + '/user/update', data, httpOptions);
  }

  getSocialTypes(): Observable<SocialType[]> {
    return this.http.get<SocialType[]>(this.baseUrl + "socialtypes/list");
  }

  updateSocialItem(socialData:SocialData): Observable<Social> {
    return this.http.patch<Social>(this.baseUrl + "portfolio/social/update", socialData, httpOptions);
  }

  //CORREGIR DESDE ACÁ

  addSocialItem(portfolio:Portfolio, socialData:SocialData): Observable<Social> {
    return this.http.post<Social>(this.baseUrl + portfolio.name + "/social/add", socialData, httpOptions);
  }

  deleteSocialItem(social:Social): Observable<any> {
    return this.http.delete<any>(this.baseUrl + "deleteitem/social/" + social.id)
  }

  changeOrderItem(orderData:OrderData): Observable<string>{
    return this.http.patch(this.baseUrl + "changeorder", orderData, {responseType: 'text'});
  }

  sendContact(emailData:EmailData):Observable<any>{
    return this.http.post(this.baseUrl + "sendcontact", emailData, httpOptions);
  }

  addItem(portfolio:Portfolio, item:any, section:string): Observable<any> {
    return this.http.post<any>(this.baseUrl + portfolio.name + "/" + section + "/add", item, httpOptions);
  }

  deleteItem(item:any, section:string): Observable<any> {
    return this.http.delete(this.baseUrl + "deleteitem/" + section + "/" + item.id);
  }

  updateItem(data:any, section:string): Observable<any> {
    return this.http.patch<any>(this.baseUrl + section + "/update", data, httpOptions);
  }

  addProjectImage(project:Project, imageData:ProjectImageData): Observable<ProjectImage> {
    return this.http.post<ProjectImage>(this.baseUrl + project.id + "/image/add", imageData, httpOptions);
  }

}
