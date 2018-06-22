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
  selector: 'app-new-diagnosis',
  templateUrl: './new-diagnosis.component.html',
  styleUrls: ['./new-diagnosis.component.css']
})
export class NewDiagnosisComponent implements OnInit {

  patientId: number;

  diagnosis = new Diagnosis();

  allSymptoms = new Array<Symptom>();
  allMedicines = new Array<Medicine>();
  allDiseases = new Array<Disease>();

  selectedSymptomName: string;
  selectedMedicineName: string;
  selectedDiseaseName: string;

  selectedSymptoms = Array<Symptom>();
  selectedMedicines = Array<Medicine>();
  selectedDisease = new Disease();

  suggestedDiseases = Array<Disease>();

  constructor(private symptomService: SymptomService,
    private diseaseService: DiseaseService,
    private medicineService: MedicineService,
    private diagnosisService: DiagnosisService,
    private route: ActivatedRoute,
    private router: Router,
    private toastr: ToastrService) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.patientId = params['id'];
      this.getAllSymptoms();
      this.getAllMedicine();
      this.getAllDiseases();
    });
  }

  private getAllSymptoms(): void {
    this.symptomService.findAll().subscribe(result => {
      this.allSymptoms = result;
    });
  }

  private getAllMedicine(): void {
    this.medicineService.findAll().subscribe(result => {
      this.allMedicines = result;
    });
  }

  private getAllDiseases(): void {
    this.diseaseService.findAll().subscribe(result => {
      this.allDiseases = result;
    });
  }

  symptomSelected(event) {
    if (!this.selectedSymptoms.some(x => x.id === event.item.id)) {
      this.selectedSymptoms.push(event.item);
    }
    this.selectedSymptomName = '';
  }

  medicineSelected(event) {
    if (!this.selectedMedicines.some(x => x.id === event.item.id)) {
      this.selectedMedicines.push(event.item);
    }
    this.selectedMedicineName = '';
  }

  diseaseSelected(event) {
    this.selectedDisease = event.item;
    this.selectedDiseaseName = '';
  }

  removeSymptom(symptom: Symptom) {
    const index: number = this.selectedSymptoms.indexOf(symptom);
    if (index !== -1) {
      this.selectedSymptoms.splice(index, 1);
      this.suggestedDiseases = [];
    }
  }

  removeMedicine(medicine: Medicine) {
    const index: number = this.selectedMedicines.indexOf(medicine);
    if (index !== -1) {
      this.selectedMedicines.splice(index, 1);
    }
  }

  suggestDiseases() {
    this.diseaseService.suggestDiseases(this.selectedSymptoms, this.patientId).subscribe(result => {
      if (result.length === 0) {
        this.toastr.warning('No diseases matching symptoms');
      }
      this.suggestedDiseases = result;
    });
  }

  create() {
    this.diagnosis.disease = this.selectedDisease;
    this.diagnosis.medicines = this.selectedMedicines;
    this.diagnosisService.create(this.diagnosis, { 'patientId': this.patientId }).subscribe(diagnosis => {
      this.toastr.success('Diagnosis created');
      this.router.navigate(['diagnoses', diagnosis.id]);
    });
  }
}
