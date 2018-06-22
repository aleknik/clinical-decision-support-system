import { Injectable } from '@angular/core';
import { RestService } from './rest.service';
import { User } from '../../shared/model/user.model';
import { HttpClient } from '@angular/common/http';
import { ToastrService } from 'ngx-toastr';

@Injectable({
  providedIn: 'root'
})
export class DoctorService extends RestService<User> {

  constructor(http: HttpClient, toastr: ToastrService) {
    super(http, '/api/doctors', toastr);
  }
}
