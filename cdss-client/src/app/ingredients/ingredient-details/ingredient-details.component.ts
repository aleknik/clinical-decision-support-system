import { Component, OnInit } from '@angular/core';
import { IngredientService } from '../../core/http/ingredient.service';
import { Router, ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Ingredient } from '../../shared/model/ingredient.model';

@Component({
  selector: 'app-ingredient-details',
  templateUrl: './ingredient-details.component.html',
  styleUrls: ['./ingredient-details.component.css']
})
export class IngredientDetailsComponent implements OnInit {

  ingredient = new Ingredient();

  id: number;

  constructor(private ingredientService: IngredientService,
    private router: Router,
    private route: ActivatedRoute,
    private toastr: ToastrService) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.id = params['id'];
      this.getIngredient();

    });
  }

  getIngredient() {
    this.ingredientService.findById(this.id).subscribe(result => {
      this.ingredient = result;
    });
  }

  save() {
    this.ingredientService.update(this.id, this.ingredient).subscribe(result => {
      this.toastr.success('Ingredient updated');
    });
  }
}
