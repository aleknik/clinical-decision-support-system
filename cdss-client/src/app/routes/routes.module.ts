import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';

import { IsUnauthenticatedGuard } from './is-unauthenticated.guard';
import { SigninComponent } from '../auth/signin/signin.component';

const routes: Routes = [
  { path: '', redirectTo: '/signin', pathMatch: 'full' },

  // Auth
  { path: 'signin', component: SigninComponent, canActivate: [IsUnauthenticatedGuard] }
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule],
  providers: [
    IsUnauthenticatedGuard]
})
export class RoutesModule { }
