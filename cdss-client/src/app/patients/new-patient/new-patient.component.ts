import { Component, OnInit } from '@angular/core';
import { Patient } from '../../shared/model/patient.model';
import { PatientService } from '../../core/http/patient.service';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Medicine } from '../../shared/model/medicine.model';
import { MedicineService } from '../../core/http/medicine.service';
import { Ingredient } from '../../shared/model/ingredient.model';
import { IngredientService } from '../../core/http/ingredient.service';

@Component({
  selector: 'app-new-patient',
  templateUrl: './new-patient.component.html',
  styleUrls: ['./new-patient.component.css']
})
export class NewPatientComponent implements OnInit {
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
    private router: Router,
    private toastr: ToastrService) { }

  ngOnInit() {
    this.getAllMedicine();
    this.getAllIngredients();
  }

  create(): void {
    this.patient.ingredientAllergies = this.selectedIngredients;
    this.patient.medicineAllergies = this.selectedMedicines;
    this.patientService.create(this.patient).subscribe(result => {
      this.toastr.success('Patient created');
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
      this.selectedMedicineName = '';
    }
  }

  ingredientSelected(event) {
    if (!this.selectedIngredients.some(x => x.id === event.itm.id)) {
      this.selectedIngredients.push(event.item);
      this.selectedIngredientName = '';
    }
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
}
