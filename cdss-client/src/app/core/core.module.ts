import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavComponent } from './nav/nav.component';
import { RoutesModule } from '../routes/routes.module';
import { SharedModule } from '../shared/shared.module';
import { ToastrModule } from 'ngx-toastr';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { TokenUtilsService } from './util/token-utils.service';
import { AuthService } from './http/auth.service';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { TokenInterceptorService } from './http/token-interceptor.service';
import { PatientService } from './http/patient.service';
import { MedicineService } from './http/medicine.service';
import { IngredientService } from './http/ingredient.service';
import { DiseaseService } from './http/disease.service';
import { DiagnosisService } from './http/diagnosis.service';
import { ReportService } from './http/report.service';
import { DoctorService } from './http/doctor.service';
import { StompConfig, StompService } from '@stomp/ng2-stompjs';

const stompConfig: StompConfig = {
  // Which server?
  url: 'ws://localhost:8080/api/notifications',

  // Headers
  // Typical keys: login, passcode, host
  headers: {
    login: 'guest',
    passcode: 'guest'
  },

  // How often to heartbeat?
  // Interval in milliseconds, set to 0 to disable
  heartbeat_in: 0, // Typical value 0 - disabled
  heartbeat_out: 20000, // Typical value 20000 - every 20 seconds
  // Wait in milliseconds before attempting auto reconnect
  // Set to 0 to disable
  // Typical value 5000 (5 seconds)
  reconnect_delay: 5000,

  // Will log diagnostics on console
  debug: true
};



@NgModule({
  imports: [
    CommonModule,
    RoutesModule,
    SharedModule,
    HttpClientModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot({
      preventDuplicates: true,
      positionClass: 'toast-bottom-right'
    })
  ],
  declarations: [NavComponent],
  exports: [NavComponent, RoutesModule],
  providers: [
    TokenUtilsService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptorService,
      multi: true
    },
    StompService,
    {
      provide: StompConfig,
      useValue: stompConfig
    },
    AuthService,
    PatientService,
    MedicineService,
    IngredientService,
    DiseaseService,
    DiagnosisService,
    ReportService,
    DoctorService
  ]
})
export class CoreModule { }
