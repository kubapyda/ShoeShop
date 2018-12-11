import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';

import { Global } from './../services/global.servie';
import { NgbRatingConfig } from '@ng-bootstrap/ng-bootstrap';
import { OrderService } from '../services/order.service';
import { RateService } from './../services/rate.service';
import { ShoeRate } from './../objects/shoe-rate';
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
  variants: Variant;
  gender: Array<{ value: string, viewValue: string }>;
  id: number;
  variantId: number;
  selectedSize = -1;
  toShoppingCart: { variantId: number, size: number, quantity: number } = { variantId: null, size: null, quantity: 1 };
  shoeRate: ShoeRate = new ShoeRate();

  constructor(
    public global: Global,
    private shoesService: ShoesService,
    private router: ActivatedRoute,
    private order: OrderService,
    private route: Router,
    private rateService: RateService,
    config: NgbRatingConfig
  ) {
    config.readonly = true;
  }

  ngOnInit() {
    this.getShoesDetail();
    this.getShoeRate();
  }

  getShoesDetail() {
    this.variantId = +this.router.snapshot.params['variantId'];
    this.id = +this.router.snapshot.params['id'];
    this.global.loaderTrue();
    this.toShoppingCart.variantId = this.variantId;
    this.shoesService.findShoesById(this.id).subscribe(shoes => {
      this.shoes = shoes;
      this.variants = this.shoes.variants.find(variant => variant.id === this.variantId);
      this.global.loaderFalse();
    });
  }

  private getShoeRate() {
    this.rateService.shoeRate(this.id).subscribe((data: ShoeRate) => {
      this.shoeRate = data;
    });
  }

  vote(id: number, isUseful: boolean) {
    this.rateService.vote({ rateId: id, isUseful: isUseful }).subscribe(() => {});
  }

  selectSize(size: number, index: number) {
    this.toShoppingCart.size = size;
    this.selectedSize = index;
  }

  getOtherVariant(variantId: number) {
    this.variantId = variantId;
    this.variants = this.shoes.variants.find(variant => variant.id === variantId);
    this.selectedSize = -1;
  }

  addToShopingCart() {
    this.order.addProduct({
      image: `${this.global.apiAddress}/shoes/${this.variantId}/picture`,
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
