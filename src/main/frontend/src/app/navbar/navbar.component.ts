import { Component, OnInit } from '@angular/core';

import { LoginComponent } from '../login/login.component';
import { LoginService } from '../services/login.service';
import { MatDialog } from '@angular/material';
import { OrderService } from './../services/order.service';
import { Router } from '@angular/router';
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
    public dialog: MatDialog,
    public loginService: LoginService,
    public order: OrderService,
    private router: Router
  ) { }

  ngOnInit() {
    this.order.variants = this.order.getProduct();
    this.order.getTotalPrice();
  }

  openLoginDialog() {
    let dialogRef = this.dialog.open(LoginComponent, {
      width: '400px'
    });
  }

  signOut() {
    localStorage.removeItem('Authorization');
    this.router.navigate(['/']);
  }

  searchByPhrase() {
    this.shoesService.searchByPhrase(this.searchText);
  }

  searchByGender(gender: string) {
    setTimeout(() => {
      this.shoesService.searchByPhrase(gender);
    });
  }

}
