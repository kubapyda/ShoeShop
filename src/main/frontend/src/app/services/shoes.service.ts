import 'rxjs/add/operator/toPromise';

import { Filters } from './../objects/filters';
import { Global } from './global.servie';
import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Shoes } from './../add-shoes/shoes';

@Injectable()
export class ShoesService {
  private url: string;
  public shoes: any;
  public totalItems: number;
  public size: number = 8;

  constructor(private http: HttpClient, private global: Global) {
    this.url = `${global.apiAddress}/shoes`;
  }

  findShoes(page: number) {
    this.global.loaderTrue();
    this.http.get(`${this.url}/find?page=${page}&size=${this.size}`).subscribe((data: any) => {
      this.shoes = data.content;
      this.totalItems = data.totalElements;
      this.global.loaderFalse();
    });
  }

  findShoesById(id: number) {
    return this.http.get<Shoes>(`${this.url}/find/id/${id}`);
  }

  addShoes(shoes: Shoes) {
    return this.http.post(`${this.url}/add`, shoes).toPromise();
  }

  filterShoes(filters: Filters, page: number) {
    this.global.loaderTrue();
    this.http.post(`${this.url}/find?page=0&size=${this.size}`, filters).subscribe((data: any) => {
      this.shoes = data.content;
      console.log(data);
      this.totalItems = data.totalElements;
      this.global.loaderFalse();
    });
  }

  searchByPhrase(search: string) {
    this.global.loaderTrue();
    this.http.get(`${this.url}/find/${search}`).subscribe((data: any) => {
      this.shoes = data;
      this.global.loaderFalse();
    });
  }

}
