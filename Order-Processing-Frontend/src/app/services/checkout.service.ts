import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SignInOutService } from './sign-in-out.service';
import { environment } from 'src/environments/environment';
import { CheckoutData } from '../models/CheckoutData';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root',
})
export class CheckoutService {
  constructor(
    private http: HttpClient,
    private signInOutService: SignInOutService
  ) {}

  checkout(checkoutData: CheckoutData): Observable<string> {
    return this.http.put<string>(`${environment.baseUrl}/checkout/cart`, checkoutData, {
        params: { 
          sessionId: this.signInOutService.getSignedInUserSessionID() 
        },
      }
    );
  }
}
