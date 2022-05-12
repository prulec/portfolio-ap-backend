import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Portfolio } from '../Portfolio';

@Injectable({
  providedIn: 'root'
})
export class PortfolioService {

  portfolioUrl:string = "http://localhost:8080/portfolio2/edit";

  constructor(private http:HttpClient) { }

  getPortfolio(): Observable<Portfolio> {
    return this.http.get<Portfolio>(this.portfolioUrl);
  }
}
