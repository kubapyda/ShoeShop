import { Component, OnInit } from '@angular/core';

import { Filters } from '../objects/filters';
import { Global } from './../services/global.servie';
import { ShoesService } from '../services/shoes.service';

@Component({
  selector: 'app-filters',
  templateUrl: './filters.component.html',
  styleUrls: ['./filters.component.scss']
})
export class FiltersComponent implements OnInit {

  brands: Array<{ value: string, viewValue: string }>;
  colors: Array<{ value: string, viewValue: string }>;
  filters: Filters = new Filters();

  constructor(
    private global: Global,
    private shoesService: ShoesService
  ) { }

  ngOnInit() {
    this.brands = this.global.brands;
    this.colors = this.global.colors;
  }

  filter() {
    this.shoesService.filterShoes(this.filters);
  }

}
