import { Injectable } from '@angular/core';
import { RestService } from './rest.service';
import { ToastrService } from 'ngx-toastr';
import { Medicine } from '../../shared/model/medicine.model';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class MedicineService extends RestService<Medicine> {

  constructor(http: HttpClient, toastr: ToastrService) {
    super(http, '/api/medicines', toastr);
  }
}
