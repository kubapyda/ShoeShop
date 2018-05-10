import { CanActivate, Router } from '@angular/router';

import { Injectable } from '@angular/core';
import { LoginService } from './login.service';

@Injectable()
export class AuthGuard implements CanActivate {

  constructor(
    private router: Router,
    private loginService: LoginService) { }

  canActivate() {
    if (this.loginService.getToken()) {
      return true;
    }

    this.router.navigate(['/']);
    return false;
  }

}
