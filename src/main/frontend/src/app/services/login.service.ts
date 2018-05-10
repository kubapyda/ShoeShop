import { Global } from './global.servie';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable()
export class LoginService {

  url: string;

  constructor(private http: HttpClient, private global: Global) {
    this.url = `${global.apiAddress}/accounts/signIn`;
  }

  getToken() {
    return localStorage.getItem('Authorization');
  }

  signIn(login: { login: string, password: string }) {
    return this.http.post(this.url, login, { observe: 'response' });
  }

}
