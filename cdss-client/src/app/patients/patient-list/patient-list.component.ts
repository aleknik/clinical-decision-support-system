import { Component, OnInit } from '@angular/core';
import { Patient } from '../../shared/model/patient.model';
import { PatientService } from '../../core/http/patient.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-patient-list',
  templateUrl: './patient-list.component.html',
  styleUrls: ['./patient-list.component.css']
})
export class PatientListComponent implements OnInit {
  patients: Patient[];

  constructor(private patientService: PatientService,
    private router: Router) {}

  ngOnInit() {
    this.findAllPatients();
  }

  private findAllPatients(): void {
    this.patientService.findAll().subscribe(result => {
      this.patients = result;
    });
  }

  onSelect(patient) {
    this.router.navigate(['patients', patient.id]);
  }
}
