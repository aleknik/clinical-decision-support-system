import { Component, OnInit } from '@angular/core';
import { User } from '../../shared/model/user.model';
import { AuthService } from '../../core/http/auth.service';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css']
})
export class SigninComponent implements OnInit {

  user: User = new User();

  constructor(private authService: AuthService,
    private router: Router,
    private toastr: ToastrService) { }

  ngOnInit() {
  }

  signin() {
    this.authService.authenticate(this.user).subscribe(
      response => {
        this.toastr.success(`Welcome ${this.user.username}`);
        this.router.navigateByUrl('');
      },
      err => { });
  }

}
