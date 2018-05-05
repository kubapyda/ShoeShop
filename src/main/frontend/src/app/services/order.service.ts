import { Global } from './global.servie';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Receiver } from '../objects/receiver';

@Injectable()
export class OrderService {

  variants: Array<{ variantId: number, size: number, quantity: number, brand: string, model: string, price: number }> = [];
  receiver: Receiver = new Receiver();
  totalPrice: number = 0;
  url: string;
  localStorageItem: string = 'shoppingCart';

  constructor(private http: HttpClient, private global: Global) {
    this.url = `${global.apiAddress}/orders`;
  }

  makeOrder(order: { receiver: Receiver, variants: Array<{ variantId: number, size: number, quantity: number }> }) {
    return this.http.put(`${this.url}/order`, order);
  }

  addProduct(product: { variantId: number, size: number, quantity: number, brand: string, model: string, price: number }) {
    this.variants = JSON.parse(localStorage.getItem(this.localStorageItem));
    const shoppingCartIndex = this.variants.findIndex(variant => variant.variantId === product.variantId && variant.size === product.size);
    shoppingCartIndex === -1 ? this.variants.push(product) : this.variants[shoppingCartIndex].quantity++;
    localStorage.setItem(this.localStorageItem, JSON.stringify(this.variants));
  }

  removeProduct(idx: number) {
    this.variants = JSON.parse(localStorage.getItem(this.localStorageItem));
    this.variants.splice(idx, 1);
    localStorage.setItem(this.localStorageItem, JSON.stringify(this.variants));
  }

  saveProductInShoppingCart() {
    localStorage.setItem(this.localStorageItem, JSON.stringify(this.variants));
  }

  getTotalPrice() {
    this.totalPrice = 0;
    this.variants.forEach(product => {
      this.totalPrice += product.price * +product.quantity;
    });
  }

  getProduct() {
    this.variants = JSON.parse(localStorage.getItem(this.localStorageItem));
    return this.variants;
  }

}
