import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NewDiagnosisComponent } from './new-diagnosis/new-diagnosis.component';
import { RouterModule } from '@angular/router';
import { SharedModule } from '../shared/shared.module';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    SharedModule
  ],
  declarations: [NewDiagnosisComponent]
})
export class DiagnosisModule { }
