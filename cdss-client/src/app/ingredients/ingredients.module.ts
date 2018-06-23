import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { SharedModule } from '../shared/shared.module';
import { NewIngredientComponent } from './new-ingredient/new-ingredient.component';
import { IngredientListComponent } from './ingredient-list/ingredient-list.component';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    SharedModule,
  ],
  declarations: [NewIngredientComponent, IngredientListComponent]
})
export class IngredientsModule { }
