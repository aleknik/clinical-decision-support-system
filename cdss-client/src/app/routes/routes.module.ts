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
import { NewDoctorComponent } from '../doctors/new-doctor/new-doctor.component';
import { IngredientListComponent } from '../ingredients/ingredient-list/ingredient-list.component';
import { MedicineListComponent } from '../medicines/medicine-list/medicine-list.component';
import { DoctorListComponent } from '../doctors/doctor-list/doctor-list.component';
import { IngredientDetailsComponent } from '../ingredients/ingredient-details/ingredient-details.component';
import { MedicineDetailsComponent } from '../medicines/medicine-details/medicine-details.component';
import { NewDiseaseComponent } from '../diseases/new-disease/new-disease.component';
import { DiseaseListComponent } from '../diseases/disease-list/disease-list.component';

const routes: Routes = [
  { path: '', redirectTo: '/reports', pathMatch: 'full' },

  { path: 'signin', component: SigninComponent, canActivate: [IsUnauthenticatedGuard] },

  { path: 'patients', component: PatientListComponent, canActivate: [IsDoctorGuard] },
  { path: 'patients/new', component: NewPatientComponent, canActivate: [IsDoctorGuard] },
  { path: 'patients/:id', component: PatientDetailsComponent, canActivate: [IsDoctorGuard] },

  { path: 'patients/:id/diagnoses', component: DiagnosisListComponent, canActivate: [IsDoctorGuard] },
  { path: 'patients/:id/diagnoses/new', component: NewDiagnosisComponent, canActivate: [IsDoctorGuard] },
  { path: 'diagnoses/:id', component: DiagnosisDetailsComponent, canActivate: [IsDoctorGuard] },

  { path: 'reports', component: ReportDetailsComponent, canActivate: [IsAuthenticatedGuard] },

  { path: 'ingredients', component: IngredientListComponent, canActivate: [IsAdminGuard] },
  { path: 'ingredients/new', component: NewIngredientComponent, canActivate: [IsAdminGuard] },
  { path: 'ingredients/:id', component: IngredientDetailsComponent, canActivate: [IsAdminGuard] },

  { path: 'medicines', component: MedicineListComponent, canActivate: [IsAdminGuard] },
  { path: 'medicines/new', component: NewMedicineComponent, canActivate: [IsAdminGuard] },
  { path: 'medicines/:id', component: MedicineDetailsComponent, canActivate: [IsAdminGuard] },

  { path: 'doctors', component: DoctorListComponent, canActivate: [IsAdminGuard] },
  { path: 'doctors/new', component: NewDoctorComponent, canActivate: [IsAdminGuard] },

  { path: 'diseases', component: DiseaseListComponent, canActivate: [IsAdminGuard] },
  { path: 'diseases/new', component: NewDiseaseComponent, canActivate: [IsAdminGuard] },

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
