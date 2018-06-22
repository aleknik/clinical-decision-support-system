import { Component, OnInit } from '@angular/core';
import { AuthService } from '../http/auth.service';
import { Router } from '@angular/router';
import { DOCTOR } from '../util/constants';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent implements OnInit {

  isCollapsed = true;

  constructor(private authService: AuthService,
    private router: Router) { }

  ngOnInit() {
  }

  isAuthenticated(): boolean {
    return this.authService.isAuthenticated();
  }

  isDoctor(): boolean {
    return this.authService.checkPermission(DOCTOR);
  }

  signout() {
    this.authService.signout();
    this.router.navigateByUrl('signin');
  }
}
