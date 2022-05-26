import { Injectable } from '@angular/core';

const TOKEN_KEY = 'AuthToken';

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  constructor() { }

  setToken(token: string) {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY, token);
  }

  getToken(): string {
    return sessionStorage.getItem(TOKEN_KEY) || "";
  }

  logout() {
    window.sessionStorage.clear();
  }

  isLogged(): boolean {
    if (sessionStorage.getItem(TOKEN_KEY)) return true;
    return false;
  }

  getUsername(): string {
    if (!this.isLogged()) return "";
    const payload = this.getToken().split(".")[1];
    const decodedPayload = atob(payload);
    const values = JSON.parse(decodedPayload);
    const username = values.sub;
    return username;
  }

}
