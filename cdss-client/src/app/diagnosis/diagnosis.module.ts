import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NewDiagnosisComponent } from './new-diagnosis/new-diagnosis.component';
import { RouterModule } from '@angular/router';
import { SharedModule } from '../shared/shared.module';
import { DiagnosisListComponent } from './diagnosis-list/diagnosis-list.component';
import { DiagnosisDetailsComponent } from './diagnosis-details/diagnosis-details.component';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    SharedModule
  ],
  declarations: [NewDiagnosisComponent, DiagnosisListComponent, DiagnosisDetailsComponent]
})
export class DiagnosisModule { }
