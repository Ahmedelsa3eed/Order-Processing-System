import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { SignInComponent } from './components/sign-in/sign-in.component';
import { RegistrationComponent } from "./components/registration/registration.component";
import { CartComponent } from './components/cart/cart.component';
import { BooksPageComponent } from './components/books-page/books-page.component';
import { OrdersPageComponent } from './components/orders-page/orders-page.component';
import { PublisherComponent } from './components/publisher/publisher.component';
import {SettingsComponent} from "./components/settings/settings.component";
import { AuthorComponent } from "./components/author/author.component";
import { AccountsPageComponent } from './components/accounts-page/accounts-page.component';


const routes: Routes = [
  { path: 'sign-in', component: SignInComponent },
  { path: '', redirectTo: 'sign-in', pathMatch: 'full' },
  { path: 'sign-up', component: RegistrationComponent },
  {
    path: 'home',
    component: HomeComponent,
    children: [
      { path: 'cart', component: CartComponent },
      { path: 'books', component: BooksPageComponent},
      { path: 'orders', component: OrdersPageComponent},
      { path: 'publishers', component: PublisherComponent},
      { path: 'authors', component: AuthorComponent },
      { path: 'accounts', component: AccountsPageComponent},
      { path: 'settings', component: SettingsComponent},
    ],
  },
  { path: '**', component: SignInComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
