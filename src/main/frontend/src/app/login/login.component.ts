import { Component, OnInit, ViewContainerRef } from '@angular/core';

import { Global } from '../services/global.servie';
import { LoginService } from '../services/login.service';
import { MatDialogRef } from '@angular/material';
import { Router } from '@angular/router';
import { ToastsManager } from 'ng2-toastr';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  login = {
    login: null,
    password: null
  };

  constructor(
    public global: Global,
    public dialogRef: MatDialogRef<LoginComponent>,
    private router: Router,
    private loginService: LoginService,
    private toastr: ToastsManager,
    private vcr: ViewContainerRef
  ) {
    this.toastr.setRootViewContainerRef(vcr);
  }

  ngOnInit() {
  }

  signIn() {
    this.global.loaderTrue();
    this.loginService.signIn(this.login).subscribe((success: any) => {
      localStorage.setItem('Authorization', success.headers.get('Authorization'));
      this.global.loaderFalse();
      this.router.navigate(['/']);
      this.dialogRef.close();
    }, err => {
      this.toastr.error('Błędne dane logowania!');
      this.global.loaderFalse();
    });
  }

}
