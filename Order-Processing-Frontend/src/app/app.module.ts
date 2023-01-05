import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CartComponent } from './components/cart/cart.component';
import { CartItemComponent } from './components/cart-item/cart-item.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { SignInComponent } from './components/sign-in/sign-in.component';
import { SignInOutService } from './services/sign-in-out.service';
import { CookieService } from 'ngx-cookie-service';
import { HomeComponent } from './components/home/home.component';
import { NavigationBarComponent } from './components/navigation-bar/navigation-bar.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { BooksPageComponent } from './components/books-page/books-page.component';
import { OrdersPageComponent } from './components/orders-page/orders-page.component';
import { PublisherComponent } from './components/publisher/publisher.component';
import { AuthorComponent } from './components/author/author.component';
import { AccountsPageComponent } from './components/accounts-page/accounts-page.component';
import { AccountComponent } from './components/account/account.component';
import { SettingsComponent } from './components/settings/settings.component';
import { CheckoutComponent } from './components/checkout/checkout.component';


@NgModule({
  declarations: [
    AppComponent,
    CartComponent,
    CartItemComponent,
    SignInComponent,
    HomeComponent,
    NavigationBarComponent,
    RegistrationComponent,
    BooksPageComponent,
    OrdersPageComponent,
    PublisherComponent,
    AuthorComponent,
    AccountsPageComponent,
    AccountComponent,
    SettingsComponent,
    CheckoutComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FontAwesomeModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    CommonModule,
  ],
  providers: [SignInOutService, CookieService],
  bootstrap: [AppComponent]
})
export class AppModule { }
