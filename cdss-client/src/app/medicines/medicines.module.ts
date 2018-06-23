import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NewMedicineComponent } from './new-medicine/new-medicine.component';
import { RouterModule } from '@angular/router';
import { SharedModule } from '../shared/shared.module';
import { MedicineListComponent } from './medicine-list/medicine-list.component';
import { MedicineDetailsComponent } from './medicine-details/medicine-details.component';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    SharedModule,
  ],
  declarations: [NewMedicineComponent, MedicineListComponent, MedicineDetailsComponent]
})
export class MedicinesModule { }
