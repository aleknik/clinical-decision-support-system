import { Injectable } from '@angular/core';
import { Disease } from '../../shared/model/disease.nodel';
import { RestService } from './rest.service';
import { HttpClient } from '@angular/common/http';
import { ToastrService } from 'ngx-toastr';
import { Symptom } from '../../shared/model/symptom.model';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class DiseaseService extends RestService<Disease> {

  constructor(http: HttpClient, toastr: ToastrService) {
    super(http, '/api/diseases', toastr);
  }

  suggestDiseases(symptoms: Symptom[], patientId: number): Observable<Disease[]> {
    return this.http
      .post<Disease[]>(`${this.baseUrl}/suggest-diseases`, symptoms, { params: { 'patientId': String(patientId) } })
      .pipe(catchError(this.handleError<Disease[]>()));
  }
}
