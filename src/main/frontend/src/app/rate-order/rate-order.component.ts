import * as _ from 'lodash';

import { ActivatedRoute, Router } from '@angular/router';
import { Component, ViewContainerRef } from '@angular/core';

import { Rate } from '../objects/rate';
import { RateService } from './../services/rate.service';
import { ToastsManager } from 'ng2-toastr';

@Component({
  selector: 'app-rate-order',
  templateUrl: './rate-order.component.html',
  styleUrls: ['./rate-order.component.scss']
})
export class RateOrderComponent {

  rateValue: number;
  comment: string;

  constructor(
    private route: ActivatedRoute,
    private rateService: RateService,
    private toastr: ToastsManager,
    private router: Router,
    private vcr: ViewContainerRef
  ) {
    this.toastr.setRootViewContainerRef(vcr);
    console.log(this.route);
  }

  addRating() {
    const rate: Rate = {
      rate: this.rateValue,
      comment: this.comment,
      // identityEmail: _.get(this.route.queryParams, 'value.email'),
      identityEmail: 'kubapyda95@gmail.com',
      orderId: +_.get(this.route.params, 'value.orderId'),
      shoeId: +_.get(this.route.params, 'value.shoeId')
    };
    this.rateService.rate(rate).subscribe((data) => {
      console.log(data);
      this.toastr.success('Pomyślnie oceniono obuwie. Zostaniesz przekierowany na stronę główną', 'Dziękujemy!');
      setTimeout(() => {
        this.router.navigate(['']);
      }, 4000);
    }, (err) => {
      console.log(err);
      this.toastr.error('Podczas dodawania oceny wystąpił błąd, prosimy spróbować ponownie później', 'Błąd!');
    });

  }

}
