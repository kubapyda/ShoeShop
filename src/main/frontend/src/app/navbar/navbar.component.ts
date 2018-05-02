import { Component, OnInit } from '@angular/core';

import { OrderService } from './../services/order.service';
import { ShoesService } from './../services/shoes.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  searchText: string;

  constructor(
    public shoesService: ShoesService,
    public order: OrderService
  ) { }

  ngOnInit() {
    this.order.variants = this.order.getProduct();
    this.order.getTotalPrice();
  }

  searchByPhrase() {
    this.shoesService.searchByPhrase(this.searchText);
  }

}
