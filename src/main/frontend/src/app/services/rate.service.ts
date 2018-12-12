import { Global } from './global.servie';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Rate } from './../objects/rate';
import { Vote } from './../objects/vote';

@Injectable()
export class RateService {

  url: string;

  constructor(private http: HttpClient, private global: Global) {
    this.url = `${this.global.apiAddress}/rates`;
  }

  rate(rating: Rate) {
    return this.http.post(`${this.url}/rate`, rating);
  }

  shoeRate(id: number) {
    return this.http.get(`${this.url}/shoe/${id}`);
  }

  vote(vote: Vote) {
    return this.http.post(`${this.url}/vote`, vote);
  }

}
