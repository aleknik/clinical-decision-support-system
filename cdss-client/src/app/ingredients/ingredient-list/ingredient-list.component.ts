import { Component, OnInit } from '@angular/core';
import { Ingredient } from '../../shared/model/ingredient.model';
import { IngredientService } from '../../core/http/ingredient.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-ingredient-list',
  templateUrl: './ingredient-list.component.html',
  styleUrls: ['./ingredient-list.component.css']
})
export class IngredientListComponent implements OnInit {

  ingredients: Ingredient[];

  constructor(private ingredientService: IngredientService,
    private router: Router) { }

  ngOnInit() {
    this.findAllIngredients();
  }

  private findAllIngredients(): void {
    this.ingredientService.findAll().subscribe(result => {
      this.ingredients = result;
    });
  }

  onSelect(ingredient) {
    this.router.navigate(['ingredients', ingredient.id]);
  }
}
