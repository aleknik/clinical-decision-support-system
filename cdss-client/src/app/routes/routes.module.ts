import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';

import { IsUnauthenticatedGuard } from './is-unauthenticated.guard';
import { SigninComponent } from '../auth/signin/signin.component';
import { PatientListComponent } from '../patients/patient-list/patient-list.component';
import { IsAuthenticatedGuard } from './is-authenticated.guard';
import { PatientDetailsComponent } from '../patients/patient-details/patient-details.component';
import { NewPatientComponent } from '../patients/new-patient/new-patient.component';

const routes: Routes = [
  { path: '', redirectTo: '/patients', pathMatch: 'full' },

  // Auth
  { path: 'signin', component: SigninComponent, canActivate: [IsUnauthenticatedGuard] },

  { path: 'patients', component: PatientListComponent, canActivate: [IsAuthenticatedGuard] },
  { path: 'patients/new', component: NewPatientComponent, canActivate: [IsAuthenticatedGuard] },
  { path: 'patients/:id', component: PatientDetailsComponent, canActivate: [IsAuthenticatedGuard] },
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule],
  providers: [
    IsUnauthenticatedGuard,
    IsAuthenticatedGuard]
})
export class RoutesModule { }
