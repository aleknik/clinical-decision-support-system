import { Component, OnInit } from '@angular/core';
import { Symptom } from '../../shared/model/symptom.model';
import { SymptomService } from '../../core/http/symptom.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Disease } from '../../shared/model/disease.model';
import { DiseaseService } from '../../core/http/disease.service';
import { Medicine } from '../../shared/model/medicine.model';
import { MedicineService } from '../../core/http/medicine.service';
import { Diagnosis } from '../../shared/model/diagnosis.model';
import { DiagnosisService } from '../../core/http/diagnosis.service';


@Component({
  selector: 'app-diagnosis-details',
  templateUrl: './diagnosis-details.component.html',
  styleUrls: ['./diagnosis-details.component.css']
})
export class DiagnosisDetailsComponent implements OnInit {
  diagnosisId: number;

  diagnosis = new Diagnosis();

  constructor(private diagnosisService: DiagnosisService,
    private route: ActivatedRoute,
    private router: Router,
    private toastr: ToastrService) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.diagnosisId = params['id'];
      this.getDiagnosis();
    });
  }

  getDiagnosis() {
    this.diagnosisService.findById(this.diagnosisId).subscribe(result => {
      this.diagnosis = result;
    });
  }

}
