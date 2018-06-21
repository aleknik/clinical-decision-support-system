import { Injectable } from '@angular/core';
import { RestService } from './rest.service';
import { Patient } from '../../shared/model/patient.model';
import { ToastrService } from 'ngx-toastr';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PatientService extends RestService<Patient> {

  constructor(http: HttpClient, toastr: ToastrService) {
    super(http, '/api/patients', toastr);
  }
}
