import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { SignInComponent } from './components/sign-in/sign-in.component';
import { CartComponent } from './components/cart/cart.component';
import { BooksPageComponent } from './components/books-page/books-page.component';


const routes: Routes = [
  { path: 'sign-in', component: SignInComponent },
  { path: '', redirectTo: 'sign-in', pathMatch: 'full' },
  {
    path: 'home',
    component: HomeComponent,
    children: [
      { path: 'cart', component: CartComponent },
      { path: 'books', component: BooksPageComponent }
    ],
  },
  { path: '**', component: SignInComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
