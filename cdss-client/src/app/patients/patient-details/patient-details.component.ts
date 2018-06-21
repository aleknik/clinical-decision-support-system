import { Component, OnInit } from '@angular/core';
import { Patient } from '../../shared/model/patient.model';
import { PatientService } from '../../core/http/patient.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-patient-details',
  templateUrl: './patient-details.component.html',
  styleUrls: ['./patient-details.component.css']
})
export class PatientDetailsComponent implements OnInit {

  patientId: number;

  patient = new Patient();

  constructor(private patientService: PatientService,
    private route: ActivatedRoute,
    private router: Router,
    private toastr: ToastrService) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.patientId = params['id'];
      this.getPatient();

    });
  }

  private getPatient(): void {
    this.patientService.findById(this.patientId).subscribe(result => {
      this.patient = result;
    });
  }

  update(): void {
    this.patientService.update(this.patientId, this.patient).subscribe(result => {
      this.toastr.success('Patient updated');
    });
  }

}
