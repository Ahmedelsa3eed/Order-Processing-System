import { Component, OnInit } from '@angular/core';
import { Order } from 'src/app/models/Order';
import { OrdersService } from 'src/app/services/orders.service';

@Component({
  selector: 'app-orders-page',
  templateUrl: './orders-page.component.html',
  styleUrls: ['./orders-page.component.css']
})
export class OrdersPageComponent implements OnInit {

  OrderId:number = -1;
  ConfirmOrderLoading: boolean = false;
  orders: Order[] = [];
  constructor(private ordersService: OrdersService) { }

  ngOnInit(): void {
    let test: Order = new Order();
    test.orderId = 1;
    test.ISBN = 1;
    test.quantity = 10;
    this.orders.push(test);
  }

  openConfirmOrderModal(orderId:number){
    this.OrderId = orderId;
    document.getElementById('openConfirmOrderBtn')?.click();
  }

  getBookTitle(bookISBN:number){
    return "over the horizon";
  }

  onConfirmOrder(){
    this.ConfirmOrderLoading=true;
    this.ordersService.cofirmOrder(this.OrderId).subscribe(()=>{
      this.ConfirmOrderLoading = false;
      document.getElementById('closeConfirmOrderBtn')?.click();
      window.location.reload();
    })
  }
}
