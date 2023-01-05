import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { OrderToPlace } from '../DTOs/OrderToPlace';
import { Order } from '../models/Order';
import { SignInOutService } from './sign-in-out.service';

@Injectable({
  providedIn: 'root'
})
export class OrdersService {

  constructor(private httpClient: HttpClient, private signInOutService: SignInOutService) { }
  
  orderBook(orderToPlace: OrderToPlace) {
    return this.httpClient.post(environment.baseUrl + '/orders/manager/orderBook', orderToPlace,  {
      params: { sessionId: this.signInOutService.getSignedInUserSessionID() },
    });
  }  

  cofirmOrder(orderId: number){
    return this.httpClient.delete(environment.baseUrl + '/orders/manager/confirm', {
      params: { sessionId: this.signInOutService.getSignedInUserSessionID(), orderId: orderId },
    });
  }

  getAllOrders() : Observable<any> {
    return this.httpClient.get<any>(environment.baseUrl + '/orders/manager/GetAllOrders');
  }
}
