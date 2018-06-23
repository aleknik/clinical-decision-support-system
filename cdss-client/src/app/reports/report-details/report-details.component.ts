import { Component, OnInit } from '@angular/core';
import { Report } from '../../shared/model/report.model';
import { ReportService } from '../../core/http/report.service';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-report-details',
  templateUrl: './report-details.component.html',
  styleUrls: ['./report-details.component.css']
})
export class ReportDetailsComponent implements OnInit {

  report = new Report();

  constructor(private reportService: ReportService,
    private router: Router,
    private toastr: ToastrService) { }

  ngOnInit() {
    this.getReport();
  }

  getReport() {
    this.reportService.generate().subscribe(result => {
      if (result.chronicPatients.length === 0) {
        this.toastr.warning('No chronic patients found');
      }
      if (result.addictPatients.length === 0) {
        this.toastr.warning('No addict patients found');
      }
      if (result.weakImmuneSystemPatients.length === 0) {
        this.toastr.warning('No weak immune system patients found');
      }
      this.report = result;
    });
  }

  onSelect(patient) {
    this.router.navigate(['patients', patient.id]);
  }

}
