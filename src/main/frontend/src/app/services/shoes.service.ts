import { Filters } from './../objects/filters';
import { Global } from './global.servie';
import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Shoes } from './../add-shoes/shoes';

@Injectable()
export class ShoesService {
  private url: string;
  public shoes: any;

  constructor(private http: HttpClient, private global: Global) {
    this.url = `${global.apiAddress}/shoes`;
  }

  findShoes() {
    this.global.loaderTrue();
    this.http.get(`${this.url}/find`).subscribe(data => {
      this.shoes = data;
      this.global.loaderFalse();
    });
  }

  findShoesById(id: number) {
    return this.http.get<Shoes>(`${this.url}/find/id/${id}`);
  }

  addShoes(shoes: Shoes) {
    return this.http.post(`${this.url}/add`, shoes);
  }

  filterShoes(filters: Filters) {
    this.global.loaderTrue();
    this.http.post(`${this.url}/find`, filters).subscribe(data => {
      this.shoes = data;
      this.global.loaderFalse();
    });
  }

  searchByPhrase(search: string) {
    this.global.loaderTrue();
    this.http.get(`${this.url}/find/${search}`).subscribe(data => {
      this.shoes = data;
      this.global.loaderFalse();
    });
  }

}
