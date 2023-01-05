import { SignInOutService } from './sign-in-out.service';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { CartItem } from '../models/CartItem';

@Injectable({
  providedIn: 'root',
})
export class CartService {

  private url = environment.baseUrl + '/cart'

  constructor(private http: HttpClient, private userService: SignInOutService) {}

  public getCartItems(): Observable<HttpResponse<CartItem[]>> {
    let sessionId = this.userService.getSignedInUserSessionID();
    return this.http.get<CartItem[]>(this.url + '/all', {
      observe: 'response',
      params: {
        sessionId: sessionId,
      },
      responseType: 'json',
    });
  }

  public deleteCartItem(ISBN: number): Observable<boolean> {
    let sessionId = this.userService.getSignedInUserSessionID();
    return this.http.delete<boolean>(this.url + '/deleteCartItem', {
      observe: 'body',
      params: {
        sessionId: sessionId,
        ISBN: ISBN
      },
      responseType: 'json',
    });
  }

  public updateCartItemQuantity(ISBN: number, quantity: number): Observable<boolean> {
    let sessionId = this.userService.getSignedInUserSessionID();
    return this.http.put<boolean>(this.url + '/updateQuantity', {}, {
      observe: 'body',
      params: {
        sessionId: sessionId,
        ISBN: ISBN,
        quantity: quantity,
      },
      responseType: 'json',
    });
  }

  public addToCart(ISBN: number) {
    let sessionId = this.userService.getSignedInUserSessionID();
    return this.http.post(this.url + '/addToCart', {}, {
      observe: 'body',
      params: {
        sessionId: sessionId,
        ISBN: ISBN,
      },
      responseType: 'json',
    });
  }
  
}
