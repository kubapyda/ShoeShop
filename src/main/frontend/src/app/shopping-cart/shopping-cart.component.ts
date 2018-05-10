import { Component, OnInit } from '@angular/core';

import { OrderService } from '../services/order.service';

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.scss']
})
export class ShoppingCartComponent implements OnInit {

  constructor(
    public order: OrderService
  ) { }

  ngOnInit() {
    this.order.variants = this.order.getProduct();
    this.order.getTotalPrice();
  }

  removeItem(index: number) {
    this.order.removeProduct(index);
    this.order.getTotalPrice();
  }

}
