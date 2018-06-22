import { Component, OnInit } from '@angular/core';
import { DiagnosisService } from '../../core/http/diagnosis.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Diagnosis } from '../../shared/model/diagnosis.model';

@Component({
  selector: 'app-diagnosis-list',
  templateUrl: './diagnosis-list.component.html',
  styleUrls: ['./diagnosis-list.component.css']
})
export class DiagnosisListComponent implements OnInit {

  patientId: number;

  diagnoses = new Array<Diagnosis>();

  constructor(private diagnosisService: DiagnosisService,
    private route: ActivatedRoute,
    private router: Router, ) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.patientId = params['id'];
      this.diagnosisService.findAll({ 'patientId': this.patientId }).subscribe(result => {
        this.diagnoses = result;
      });
    });
  }

  onSelect(diagnosis: Diagnosis) {
    this.router.navigate(['diagnoses', diagnosis.id]);
  }

}
