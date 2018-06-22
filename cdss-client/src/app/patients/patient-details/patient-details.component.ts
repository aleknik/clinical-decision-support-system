import { Component, OnInit } from '@angular/core';
import { Patient } from '../../shared/model/patient.model';
import { PatientService } from '../../core/http/patient.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Medicine } from '../../shared/model/medicine.model';
import { Ingredient } from '../../shared/model/ingredient.model';
import { MedicineService } from '../../core/http/medicine.service';
import { IngredientService } from '../../core/http/ingredient.service';

@Component({
  selector: 'app-patient-details',
  templateUrl: './patient-details.component.html',
  styleUrls: ['./patient-details.component.css']
})
export class PatientDetailsComponent implements OnInit {

  patientId: number;

  patient = new Patient();

  allMedicines = new Array<Medicine>();
  allIngredients = new Array<Ingredient>();

  selectedMedicineName: string;
  selectedIngredientName: string;

  selectedMedicines = Array<Medicine>();
  selectedIngredients = new Array<Ingredient>();

  constructor(private patientService: PatientService,
    private medicineService: MedicineService,
    private ingredientService: IngredientService,
    private route: ActivatedRoute,
    private router: Router,
    private toastr: ToastrService) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.patientId = params['id'];
      this.getPatient();
      this.getAllMedicine();
      this.getAllIngredients();
    });
  }

  allDiagnoses() {
    this.router.navigate(['patients', this.patientId, 'diagnoses']);
  }

  private getPatient(): void {
    this.patientService.findById(this.patientId).subscribe(result => {
      this.patient = result;
      this.selectedMedicines = this.patient.medicineAllergies;
      this.selectedIngredients = this.patient.ingredientAllergies;
    });
  }

  update(): void {
    this.patient.medicineAllergies = this.selectedMedicines;
    this.patient.ingredientAllergies = this.selectedIngredients;
    this.patientService.update(this.patientId, this.patient).subscribe(result => {
      this.toastr.success('Patient updated');
    });
  }

  private getAllMedicine(): void {
    this.medicineService.findAll().subscribe(result => {
      this.allMedicines = result;
    });
  }

  private getAllIngredients(): void {
    this.ingredientService.findAll().subscribe(result => {
      this.allIngredients = result;
    });
  }

  medicineSelected(event) {
    if (!this.selectedMedicines.some(x => x.id === event.itm.id)) {
      this.selectedMedicines.push(event.item);
    }
    this.selectedMedicineName = '';
  }

  ingredientSelected(event) {
    if (!this.selectedIngredients.some(x => x.id === event.itm.id)) {
      this.selectedIngredients.push(event.item);
    }
    this.selectedIngredientName = '';
  }

  removeMedicine(medicine: Medicine) {
    const index: number = this.selectedMedicines.indexOf(medicine);
    if (index !== -1) {
      this.selectedMedicines.splice(index, 1);
    }
  }

  removeIngredient(ingredient: Ingredient) {
    const index: number = this.selectedIngredients.indexOf(ingredient);
    if (index !== -1) {
      this.selectedIngredients.splice(index, 1);
    }
  }

  diagnosePatient() {
    this.router.navigate(['patients', this.patient.id, 'diagnoses', 'new']);
  }

}
