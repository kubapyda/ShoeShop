import * as _ from 'lodash';

import { ActivatedRoute, Router } from '@angular/router';
import { Component, ViewContainerRef } from '@angular/core';

import { Global } from './../services/global.servie';
import { MatDialog } from '@angular/material';
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
    private global: Global,
    private vcr: ViewContainerRef,
    private  dialog: MatDialog
  ) {
    this.toastr.setRootViewContainerRef(vcr);
  }

  addRating() {
    this.global.loaderTrue();
    const rate: Rate = {
      rate: this.rateValue,
      comment: this.comment,
      identityEmail: decodeURIComponent(_.get(this.route.queryParams, 'value.email')),
      orderId: +_.get(this.route.params, 'value.orderId'),
      shoeId: +_.get(this.route.params, 'value.shoeId')
    };
    this.rateService.rate(rate).subscribe(() => {
      this.global.loaderFalse();
      this.toastr.success('Pomyślnie oceniono obuwie. Zostaniesz przekierowany na stronę główną', 'Dziękujemy!');
      setTimeout(() => {
        this.router.navigate(['']);
      }, 4000);
    }, () => {
      this.global.loaderFalse();
      this.toastr.error('Podczas dodawania oceny wystąpił błąd, prosimy spróbować ponownie później', 'Błąd!');
    });

  }

}
