import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable, Injector } from '@angular/core';
import { Observable } from 'rxjs';

import { AuthService } from './auth.service';

@Injectable()
export class TokenInterceptorService implements HttpInterceptor {

  constructor(private inj: Injector) { }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const authService: AuthService = this.inj.get(AuthService);
    request = request.clone({
      setHeaders: {
        'X-Auth-Token': `${authService.getToken()}`
      }
    });

    return next.handle(request);
  }
}