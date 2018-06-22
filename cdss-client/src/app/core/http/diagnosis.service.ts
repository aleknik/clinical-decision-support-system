import { Injectable } from '@angular/core';
import { Diagnosis } from '../../shared/model/diagnosis.model';
import { RestService } from './rest.service';
import { ToastrService } from 'ngx-toastr';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class DiagnosisService extends RestService<Diagnosis> {

  constructor(http: HttpClient, toastr: ToastrService) {
    super(http, '/api/diagnoses', toastr);
  }
}
