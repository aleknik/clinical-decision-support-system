import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { SharedModule } from '../shared/shared.module';
import { NewDoctorComponent } from './new-doctor/new-doctor.component';
import { DoctorListComponent } from './doctor-list/doctor-list.component';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    SharedModule,
  ],
  declarations: [NewDoctorComponent, DoctorListComponent]
})
export class DoctorsModule { }
