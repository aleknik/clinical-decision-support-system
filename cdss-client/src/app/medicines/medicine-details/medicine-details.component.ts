import { Component, OnInit } from '@angular/core';
import { Medicine } from '../../shared/model/medicine.model';
import { Ingredient } from '../../shared/model/ingredient.model';
import { MedicineService } from '../../core/http/medicine.service';
import { IngredientService } from '../../core/http/ingredient.service';
import { Router, ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-medicine-details',
  templateUrl: './medicine-details.component.html',
  styleUrls: ['./medicine-details.component.css']
})
export class MedicineDetailsComponent implements OnInit {

  medicine = new Medicine();

  id: number;

  selectedIngredientName: string;

  allIngredients = new Array<Ingredient>();

  selectedIngredients = new Array<Ingredient>();

  constructor(private medicineService: MedicineService,
    private ingredientService: IngredientService,
    private route: ActivatedRoute,
    private router: Router,
    private toastr: ToastrService) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.id = params['id'];
      this.getMedicine();
      this.getAllIngredients();

    });
  }

  getMedicine() {
    this.medicineService.findById(this.id).subscribe(result => {
      this.medicine = result;
      this.selectedIngredients = result.ingredients;
    });
  }

  create() {
    this.medicine.ingredients = this.selectedIngredients;
    this.medicineService.update(this.id, this.medicine).subscribe(result => {
      this.toastr.success('Medicine updated');
    });
  }

  private getAllIngredients(): void {
    this.ingredientService.findAll().subscribe(result => {
      this.allIngredients = result;
    });
  }

  ingredientSelected(event) {
    if (!this.selectedIngredients.some(x => x.id === event.item.id)) {
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
