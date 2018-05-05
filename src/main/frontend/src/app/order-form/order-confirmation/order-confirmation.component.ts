import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';

import { OrderService } from './../../services/order.service';
import { Receiver } from '../../objects/receiver';
import { Router } from '@angular/router';

@Component({
  selector: 'app-order-confirmation',
  templateUrl: './order-confirmation.component.html',
  styleUrls: ['./order-confirmation.component.scss']
})
export class OrderConfirmationComponent implements OnInit {

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: { isError: boolean },
    public dialogRef: MatDialogRef<OrderConfirmationComponent>,
    private order: OrderService,
    private router: Router
  ) { }

  ngOnInit() { }

  orderConfirmation() {
    this.dialogRef.close();
    this.order.variants = [];
    this.order.receiver = new Receiver();
    this.order.saveProductInShoppingCart();
    this.order.getTotalPrice();
    this.router.navigate(['/']);
  }

  orderError() {
    this.dialogRef.close();
    this.order.receiver = new Receiver();
    this.router.navigate(['/']);
  }

}
