import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroupDirective, NgControl, NgForm, Validators } from '@angular/forms';

import { ErrorStateMatcher } from '@angular/material/core';
import { MatDialog } from '@angular/material';
import { OrderConfirmationComponent } from './order-confirmation/order-confirmation.component';
import { OrderService } from './../services/order.service';

export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}

@Component({
  selector: 'app-order-form',
  templateUrl: './order-form.component.html',
  styleUrls: ['./order-form.component.scss']
})
export class OrderFormComponent implements OnInit {
  matcher = new MyErrorStateMatcher();
  orderForm = {
    name: new FormControl('', [Validators.required]),
    surname: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.required, Validators.email]),
    city: new FormControl('', [Validators.required]),
    postalCode: new FormControl('', [Validators.required, Validators.pattern('[0-9]{2}-[0-9]{3}')]),
    street: new FormControl('', [Validators.required]),
    flatNumber: new FormControl('', [Validators.required]),
    localNumber: new FormControl(''),
  };

  constructor(
    public order: OrderService,
    public dialog: MatDialog
  ) { }

  ngOnInit() {
  }

  isButtonDisabled() {
    return this.orderForm.name.status === 'INVALID' ||
      this.orderForm.surname.status === 'INVALID' ||
      this.orderForm.email.status === 'INVALID' ||
      this.orderForm.city.status === 'INVALID' ||
      this.orderForm.postalCode.status === 'INVALID' ||
      this.orderForm.street.status === 'INVALID' ||
      this.orderForm.flatNumber.status === 'INVALID';
  }

  makeOrder() {
    const variants = this.order.variants.map(variant => {
      return {
        variantId: variant.variantId,
        size: variant.size,
        quantity: variant.quantity
      };
    });
    this.order.receiver.address.flatNumber = +this.order.receiver.address.flatNumber;
    this.order.receiver.address.localNumber = +this.order.receiver.address.localNumber;
    const order = {
      receiver: this.order.receiver,
      variants: variants
    };
    this.order.makeOrder(order).subscribe(success => {
      const orderConfirmationRef = this.dialog.open(OrderConfirmationComponent, {
        width: '60%',
        disableClose: true,
        data: { isError: false }
      });
    }, error => {
      const orderConfirmationRef = this.dialog.open(OrderConfirmationComponent, {
        width: '60%',
        disableClose: true,
        data: { isError: true }
      });
    });
  }

}
