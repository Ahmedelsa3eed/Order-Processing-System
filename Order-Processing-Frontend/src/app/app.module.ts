import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { SignInComponent } from './components/sign-in/sign-in.component';
import { SignInOutService } from './services/sign-in-out.service';
import { CookieService } from 'ngx-cookie-service';
import { HomeComponent } from './components/home/home.component';
import { NavigationBarComponent } from './components/navigation-bar/navigation-bar.component';


@NgModule({
  declarations: [
    AppComponent,
    SignInComponent,
    HomeComponent,
    NavigationBarComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    CommonModule,
  ],
  providers: [SignInOutService, CookieService],
  bootstrap: [AppComponent]
})
export class AppModule { }
