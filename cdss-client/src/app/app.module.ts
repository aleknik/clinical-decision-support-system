import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { AuthModule } from './auth/auth.module';
import { CoreModule } from './core/core.module';
import { RoutesModule } from './routes/routes.module';
import { SharedModule } from './shared/shared.module';
import { PatientsModule } from './patients/patients.module';
import { DiagnosisModule } from './diagnosis/diagnosis.module';
import { ReportsModule } from './reports/reports.module';
import { IngredientsModule } from './ingredients/ingredients.module';
import { MedicinesModule } from './medicines/medicines.module';
import { DoctorsModule } from './doctors/doctors.module';

@NgModule({
  declarations: [AppComponent],
  imports: [BrowserModule,
    AuthModule,
    CoreModule,
    RoutesModule,
    SharedModule,
    PatientsModule,
    DiagnosisModule,
    ReportsModule,
    IngredientsModule,
    MedicinesModule,
    DoctorsModule],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
