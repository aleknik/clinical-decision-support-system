import { Component, OnInit } from '@angular/core';
import { MedicineService } from '../../core/http/medicine.service';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Medicine } from '../../shared/model/medicine.model';
import { Ingredient } from '../../shared/model/ingredient.model';
import { IngredientService } from '../../core/http/ingredient.service';

@Component({
  selector: 'app-new-medicine',
  templateUrl: './new-medicine.component.html',
  styleUrls: ['./new-medicine.component.css']
})
export class NewMedicineComponent implements OnInit {

  medicine = new Medicine();

  selectedIngredientName: string;

  allIngredients = new Array<Ingredient>();

  selectedIngredients = new Array<Ingredient>();

  constructor(private medicineService: MedicineService,
    private ingredientService: IngredientService,
    private router: Router,
    private toastr: ToastrService) { }

  ngOnInit() {
    this.getAllIngredients();
  }

  create() {
    this.medicine.ingredients = this.selectedIngredients;
    this.medicineService.create(this.medicine).subscribe(result => {
      this.toastr.success('Medicine created');
    });
  }

  private getAllIngredients(): void {
    this.ingredientService.findAll().subscribe(result => {
      this.allIngredients = result;
    });
  }

  ingredientSelected(event) {
    if (!this.selectedIngredients.some(x => x.id === event.itm.id)) {
      this.selectedIngredients.push(event.item);
    }
    this.selectedIngredientName = '';
  }

  removeIngredient(ingredient: Ingredient) {
    const index: number = this.selectedIngredients.indexOf(ingredient);
    if (index !== -1) {
      this.selectedIngredients.splice(index, 1);
    }
  }

}
