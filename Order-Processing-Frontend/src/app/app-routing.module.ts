import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SignInComponent } from './components/sign-in/sign-in.component';


const routes: Routes = [
  { path: 'sign-in', component: SignInComponent },
  { path: '', redirectTo: 'sign-in', pathMatch: 'full' },
  { path: '**', component: SignInComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
