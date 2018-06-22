import { Injectable } from '@angular/core';
import { RestService } from './rest.service';
import { Report } from '../../shared/model/report.model';
import { ToastrService } from 'ngx-toastr';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ReportService extends RestService<Report> {

  constructor(http: HttpClient, toastr: ToastrService) {
    super(http, '/api/reports', toastr);
  }

  generate(): Observable<Report> {
    return this.http
      .get<Report>(`${this.baseUrl}/generate`)
      .pipe(catchError(this.handleError<Report>()));
  }
}
