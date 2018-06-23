import { Component, OnInit } from '@angular/core';
import { Disease } from '../../shared/model/disease.model';
import { Symptom } from '../../shared/model/symptom.model';
import { SymptomService } from '../../core/http/symptom.service';
import { DiseaseService } from '../../core/http/disease.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';

@Component({
  selector: 'app-new-disease',
  templateUrl: './new-disease.component.html',
  styleUrls: ['./new-disease.component.css']
})
export class NewDiseaseComponent implements OnInit {

  disease = new Disease();

  allSymptoms = new Array<Symptom>();

  selectedSymptoms1 = Array<Symptom>();
  selectedSymptomName1: string;

  selectedSymptoms2 = Array<Symptom>();
  selectedSymptomName2: string;

  constructor(private symptomService: SymptomService,
    private diseaseService: DiseaseService,
    private router: Router,
    private toastr: ToastrService) { }

  ngOnInit() {
    this.getAllSymptoms();
  }

  create() {
    this.disease.specificSymptoms = this.selectedSymptoms1;
    this.disease.generalSymptoms = this.selectedSymptoms2;
    this.diseaseService.create(this.disease).subscribe(result => {
      this.toastr.success('Disease created');
      this.router.navigate(['diseases']);
    });
  }

  private getAllSymptoms(): void {
    this.symptomService.findAll().subscribe(result => {
      this.allSymptoms = result;
    });
  }

  symptomSelected1(event) {
    if (!this.selectedSymptoms1.some(x => x.id === event.item.id)) {
      this.selectedSymptoms1.push(event.item);
    }
    this.selectedSymptomName1 = '';
  }

  symptomSelected2(event) {
    if (!this.selectedSymptoms2.some(x => x.id === event.item.id)) {
      this.selectedSymptoms2.push(event.item);
    }
    this.selectedSymptomName2 = '';
  }


  removeSymptom1(symptom: Symptom) {
    const index: number = this.selectedSymptoms1.indexOf(symptom);
    if (index !== -1) {
      this.selectedSymptoms1.splice(index, 1);
    }
  }

  removeSymptom2(symptom: Symptom) {
    const index: number = this.selectedSymptoms2.indexOf(symptom);
    if (index !== -1) {
      this.selectedSymptoms2.splice(index, 1);
    }
  }

}
