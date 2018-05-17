import {
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest
} from '@angular/common/http';

import { Injectable } from '@angular/core';
import { LoginService } from './login.service';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {
  constructor(public login: LoginService) { }
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const login = this.login.getToken();
    if (!!login) {
      const request = req.clone({
        headers: req.headers.set("Authorization", login)
      });
      return next.handle(request);
    }
    return next.handle(req);
  }
}
