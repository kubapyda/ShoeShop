import { Component, OnInit } from '@angular/core';

import { ActivatedRoute } from '@angular/router';
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
  toShoppingCart: { variant: number, size: number } = { variant: null, size: null };

  constructor(
    private shoesService: ShoesService,
    private router: ActivatedRoute
  ) { }

  ngOnInit() {
    const id: number = +this.router.snapshot.params['id'];
    const variantId: number = +this.router.snapshot.params['variantId'];
    this.toShoppingCart.variant = variantId;
    this.shoesService.findShoesById(id).subscribe(shoes => {
      this.shoes = shoes;
      this.variant = this.shoes.variants.find(variant => variant.id === variantId);
    });
  }

  selectSize(size: number) {
    this.toShoppingCart.size = size;
  }

  addToShopingCart() {
    console.log(this.toShoppingCart);
  }

}
