import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { Observable } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';

import { Authentication } from '../../shared/model/authentication.model';
import { User } from '../../shared/model/user.model';
import { TokenUtilsService } from '../util/token-utils.service';
import { RestService } from './rest.service';

const authenticatedUserKey = 'authenticatedUser';

@Injectable()
export class AuthService extends RestService<User> {

  constructor(protected http: HttpClient,
    toastr: ToastrService,
    private tokenUtils: TokenUtilsService) {
    super(http, '/api/auth', toastr);
  }

  authenticate(body: User): Observable<Authentication> {
    return this.http.post<Authentication>(this.baseUrl, body).pipe(
      tap(res => {
        localStorage.setItem(authenticatedUserKey, JSON.stringify({
          id: res.id,
          permissions: this.tokenUtils.getPermissions(res.token),
          token: res.token
        }));
      }),
      catchError(this.handleError<Authentication>())
    );
  }

  getAuthenticatedUser() {
    return JSON.parse(localStorage.getItem(authenticatedUserKey));
  }

  getAuthenticatedUserId() {
    return this.getAuthenticatedUser().id;
  }

  getToken(): String {
    const authenticatedUser = this.getAuthenticatedUser();
    const token = authenticatedUser && authenticatedUser.token;
    return token ? token : '';
  }

  getPermissions(): Array<String> {
    const authenticatedUser = this.getAuthenticatedUser();
    const permissions = authenticatedUser && authenticatedUser.permissions;
    return permissions ? permissions : new Array<String>();
  }

  checkPermission(permission: string): boolean {
    return this.getPermissions().includes(permission);
  }

  isAuthenticated(): boolean {
    return this.getToken() !== '';
  }

  signout(): void {
    localStorage.removeItem(authenticatedUserKey);
  }
}