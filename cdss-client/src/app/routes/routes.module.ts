import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';

import { IsUnauthenticatedGuard } from './is-unauthenticated.guard';
import { SigninComponent } from '../auth/signin/signin.component';
import { PatientListComponent } from '../patients/patient-list/patient-list.component';
import { IsAuthenticatedGuard } from './is-authenticated.guard';
import { PatientDetailsComponent } from '../patients/patient-details/patient-details.component';
import { NewPatientComponent } from '../patients/new-patient/new-patient.component';
import { NewDiagnosisComponent } from '../diagnosis/new-diagnosis/new-diagnosis.component';
import { DiagnosisListComponent } from '../diagnosis/diagnosis-list/diagnosis-list.component';
import { DiagnosisDetailsComponent } from '../diagnosis/diagnosis-details/diagnosis-details.component';
import { IsDoctorGuard } from './is-doctor.guard';
import { ReportDetailsComponent } from '../reports/report-details/report-details.component';
import { NewIngredientComponent } from '../ingredients/new-ingredient/new-ingredient.component';
import { IsAdminGuard } from './is-admin.guard';
import { NewMedicineComponent } from '../medicines/new-medicine/new-medicine.component';

const routes: Routes = [
  { path: '', redirectTo: '/patients', pathMatch: 'full' },

  { path: 'signin', component: SigninComponent, canActivate: [IsUnauthenticatedGuard] },

  { path: 'patients', component: PatientListComponent, canActivate: [IsDoctorGuard] },
  { path: 'patients/new', component: NewPatientComponent, canActivate: [IsDoctorGuard] },
  { path: 'patients/:id', component: PatientDetailsComponent, canActivate: [IsDoctorGuard] },

  { path: 'patients/:id/diagnoses', component: DiagnosisListComponent, canActivate: [IsDoctorGuard] },
  { path: 'patients/:id/diagnoses/new', component: NewDiagnosisComponent, canActivate: [IsDoctorGuard] },
  { path: 'diagnoses/:id', component: DiagnosisDetailsComponent, canActivate: [IsDoctorGuard] },

  { path: 'reports', component: ReportDetailsComponent, canActivate: [IsAuthenticatedGuard] },

  { path: 'ingredients/new', component: NewIngredientComponent, canActivate: [IsAdminGuard] },

  { path: 'medicines/new', component: NewMedicineComponent, canActivate: [IsAdminGuard] },

];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule],
  providers: [
    IsUnauthenticatedGuard,
    IsAuthenticatedGuard,
    IsDoctorGuard]
})
export class RoutesModule { }
