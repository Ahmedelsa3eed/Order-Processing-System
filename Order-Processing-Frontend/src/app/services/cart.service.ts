import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class CartService {

  private url = environment.baseUrl + '/cart'

  constructor(private http: HttpClient) {}

  public getCartItems(): Observable<any> {
    return this.http.get<any>('../../assets/cartItems.json', {
      responseType: 'json',
    });
  }

  public deleteCartItem(): Observable<any> {
    return this.http.delete(this.url + '/deleteCartItem');
  }
  
}
