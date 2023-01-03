import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
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

  getAllOrders(){
    return this.httpClient.get<Order[]>(environment.baseUrl + '/orders/manager/GetAllOrders', {
      params: {sessionId: this.signInOutService.getSignedInUserSessionID()},
    });
  }
}
