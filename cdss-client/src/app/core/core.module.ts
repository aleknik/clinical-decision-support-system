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

@NgModule({
  imports: [
    CommonModule,
    RoutesModule,
    SharedModule,
    HttpClientModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot({
      preventDuplicates: true,
      positionClass: 'toast-position'
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
    AuthService
  ]
})
export class CoreModule {}
