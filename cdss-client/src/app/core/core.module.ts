import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavComponent } from './nav/nav.component';
import { RoutesModule } from '../routes/routes.module';
import { SharedModule } from '../shared/shared.module';
import { ToastrModule } from 'ngx-toastr';

@NgModule({
  imports: [
    CommonModule,
    RoutesModule,
    SharedModule,
    ToastrModule.forRoot({
      preventDuplicates: true,
      positionClass: 'toast-position'
    })
  ],
  declarations: [NavComponent],
  exports: [
    NavComponent,
    RoutesModule]
})
export class CoreModule { }
