import { Global } from './global.servie';
import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Shoes } from './../add-shoes/shoes';

@Injectable()
export class ShoesService {
  private url: string;

  constructor(private http: HttpClient, private global: Global) {
    this.url = `${global.apiAddress}/shoes`;
  }

  findShoes() {
    return this.http.get(`${this.url}/find`);
  }

  addShoes(shoes: Shoes) {
    return this.http.post(`${this.url}/add`, shoes);
  }

}
