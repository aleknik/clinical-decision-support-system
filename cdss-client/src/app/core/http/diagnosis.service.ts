import { Injectable } from '@angular/core';
import { Diagnosis } from '../../shared/model/diagnosis.model';
import { RestService } from './rest.service';
import { ToastrService } from 'ngx-toastr';
import { HttpClient } from '@angular/common/http';
import { Symptom } from '../../shared/model/symptom.model';
import { Disease } from '../../shared/model/disease.model';
import { catchError } from 'rxjs/operators';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DiagnosisService extends RestService<Diagnosis> {

  constructor(http: HttpClient, toastr: ToastrService) {
    super(http, '/api/diagnoses', toastr);
  }

  connectedDiseases(symptoms: Symptom[]): Observable<Disease[]> {
    return this.http
      .post<Disease[]>(`${this.baseUrl}/diseases`, symptoms)
      .pipe(catchError(this.handleError<Disease[]>()));
  }
}
