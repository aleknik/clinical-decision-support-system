import { Injectable } from '@angular/core';
import { RestService } from './rest.service';
import { Symptom } from '../../shared/model/symptom.model';
import { HttpClient } from '@angular/common/http';
import { ToastrService } from 'ngx-toastr';

@Injectable({
  providedIn: 'root'
})
export class SymptomService extends RestService<Symptom> {

  constructor(http: HttpClient, toastr: ToastrService) {
    super(http, '/api/symptoms', toastr);
  }
}
