import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NewMedicineComponent } from './new-medicine/new-medicine.component';
import { RouterModule } from '@angular/router';
import { SharedModule } from '../shared/shared.module';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    SharedModule,
  ],
  declarations: [NewMedicineComponent]
})
export class MedicinesModule { }
