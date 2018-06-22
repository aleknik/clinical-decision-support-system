import { Injectable } from '@angular/core';
import { RestService } from './rest.service';
import { ToastrService } from 'ngx-toastr';
import { Medicine } from '../../shared/model/medicine.model';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class MedicineService extends RestService<Medicine> {

  constructor(http: HttpClient, toastr: ToastrService) {
    super(http, '/api/medicines', toastr);
  }

  checkAllergies(medicines: Medicine[], patientId: number): Observable<Medicine[]> {
    return this.http
      .post<Medicine[]>(`${this.baseUrl}/check-allergies`, medicines, { params: { 'patientId': String(patientId) } })
      .pipe(catchError(this.handleError<Medicine[]>()));
  }
}
