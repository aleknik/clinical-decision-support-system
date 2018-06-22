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

const routes: Routes = [
  { path: '', redirectTo: '/patients', pathMatch: 'full' },

  // Auth
  { path: 'signin', component: SigninComponent, canActivate: [IsUnauthenticatedGuard] },

  { path: 'patients', component: PatientListComponent, canActivate: [IsDoctorGuard] },
  { path: 'patients/new', component: NewPatientComponent, canActivate: [IsDoctorGuard] },
  { path: 'patients/:id', component: PatientDetailsComponent, canActivate: [IsDoctorGuard] },

  { path: 'patients/:id/diagnoses', component: DiagnosisListComponent, canActivate: [IsDoctorGuard] },
  { path: 'patients/:id/diagnoses/new', component: NewDiagnosisComponent, canActivate: [IsDoctorGuard] },
  { path: 'diagnoses/:id', component: DiagnosisDetailsComponent, canActivate: [IsDoctorGuard] },
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
