import { Injectable } from '@angular/core';
import { RestService } from './rest.service';
import { ToastrService } from 'ngx-toastr';
import { HttpClient } from '@angular/common/http';
import { Ingredient } from '../../shared/model/ingredient.model';

@Injectable({
  providedIn: 'root'
})
export class IngredientService extends RestService<Ingredient> {

  constructor(http: HttpClient, toastr: ToastrService) {
    super(http, '/api/ingredients', toastr);
  }
}
