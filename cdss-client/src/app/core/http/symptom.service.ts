import { Injectable } from '@angular/core';
import { RestService } from './rest.service';
import { Symptom } from '../../shared/model/symptom.model';
import { HttpClient } from '@angular/common/http';
import { ToastrService } from 'ngx-toastr';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class SymptomService extends RestService<Symptom> {

  constructor(http: HttpClient, toastr: ToastrService) {
    super(http, '/api/symptoms', toastr);
  }

  sortSymptoms(symptoms: Symptom[], diseaseId: number): Observable<Symptom[]> {
    return this.http
      .post<Symptom[]>(`${this.baseUrl}/disease`, symptoms, { params: { 'diseaseId': String(diseaseId) } })
      .pipe(catchError(this.handleError<Symptom[]>()));
  }
}
