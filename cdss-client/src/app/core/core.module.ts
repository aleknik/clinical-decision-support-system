import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavComponent } from './nav/nav.component';
import { RoutesModule } from '../routes/routes.module';
import { SharedModule } from '../shared/shared.module';

@NgModule({
  imports: [
    CommonModule,
    RoutesModule,
    SharedModule
  ],
  declarations: [NavComponent],
  exports: [
    NavComponent,
    RoutesModule]
})
export class CoreModule { }
