import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Order } from '../models/Order';
import { SignInOutService } from './sign-in-out.service';

@Injectable({
  providedIn: 'root'
})
export class OrdersService {

  constructor(private httpClient: HttpClient, private signInOutService: SignInOutService) { }
  
  orderBook(order: Order) {
    return this.httpClient.post(environment.baseUrl + '/books/manager/orderBook', order,  {
      params: { sessionId: this.signInOutService.getSignedInUserSessionID() },
    });
  }  

  cofirmOrder(orderId: number){
    return this.httpClient.delete(environment.baseUrl + '/books/manager/deleteOrder', {
      params: { sessionId: this.signInOutService.getSignedInUserSessionID(), ISBN: orderId },
    });
  }
}
