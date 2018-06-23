import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NewDiseaseComponent } from './new-disease/new-disease.component';
import { RouterModule } from '@angular/router';
import { SharedModule } from '../shared/shared.module';
import { DiseaseListComponent } from './disease-list/disease-list.component';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    SharedModule,
  ],
  declarations: [NewDiseaseComponent, DiseaseListComponent]
})
export class DiseasesModule { }
