import { Global } from './global.servie';
import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Shoes } from './../add-shoes/shoes';

@Injectable()
export class ShoesService {
  private url: string;

  constructor(private http: HttpClient, private global: Global) {
    this.url = `${global.apiAddress}/shoes/add`;
  }

  addShoes(shoes: Shoes) {
    return this.http.post(this.url, shoes);
  }

}
