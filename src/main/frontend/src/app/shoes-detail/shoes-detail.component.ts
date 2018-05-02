import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';

import { OrderService } from '../services/order.service';
import { Shoes } from '../add-shoes/shoes';
import { ShoesService } from '../services/shoes.service';
import { Variant } from './../add-shoes/add-variant/variant';

@Component({
  selector: 'app-shoes-detail',
  templateUrl: './shoes-detail.component.html',
  styleUrls: ['./shoes-detail.component.scss']
})
export class ShoesDetailComponent implements OnInit {

  shoes: Shoes;
  variant: Variant;
  gender: Array<{ value: string, viewValue: string }>;
  toShoppingCart: { variantId: number, size: number, quantity: number } = { variantId: null, size: null, quantity: 1 };

  constructor(
    private shoesService: ShoesService,
    private router: ActivatedRoute,
    private order: OrderService,
    private route: Router
  ) { }

  ngOnInit() {
    const id: number = +this.router.snapshot.params['id'];
    const variantId: number = +this.router.snapshot.params['variantId'];
    this.toShoppingCart.variantId = variantId;
    this.shoesService.findShoesById(id).subscribe(shoes => {
      this.shoes = shoes;
      this.variant = this.shoes.variants.find(variant => variant.id === variantId);
    });
  }

  selectSize(size: number) {
    this.toShoppingCart.size = size;
  }

  addToShopingCart() {
    this.order.addProduct({
      variantId: this.toShoppingCart.variantId,
      size: this.toShoppingCart.size,
      quantity: 1,
      brand: this.shoes.brand,
      model: this.shoes.model,
      price: this.shoes.price
    });
    this.route.navigate(['shopping-cart']);
  }

}
