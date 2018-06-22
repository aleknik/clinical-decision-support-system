import { Component, OnInit } from '@angular/core';
import { Ingredient } from '../../shared/model/ingredient.model';
import { IngredientService } from '../../core/http/ingredient.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';

@Component({
  selector: 'app-new-ingredient',
  templateUrl: './new-ingredient.component.html',
  styleUrls: ['./new-ingredient.component.css']
})
export class NewIngredientComponent implements OnInit {

  ingredient = new Ingredient();

  constructor(private ingredientService: IngredientService,
    private router: Router,
    private toastr: ToastrService) { }

  ngOnInit() {
  }

  create() {
    this.ingredientService.create(this.ingredient).subscribe(result => {
      this.toastr.success('Ingredient created');
    });
  }

}
