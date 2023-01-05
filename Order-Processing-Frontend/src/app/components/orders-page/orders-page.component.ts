import { Component, OnInit } from '@angular/core';
import { OrderToView } from 'src/app/DTOs/OrderToView';
import { Book } from 'src/app/models/Book';
import { Order } from 'src/app/models/Order';
import { BooksService } from 'src/app/services/books.service';
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
  ordersToView : OrderToView[] = [];
  counter:number = 0;
  constructor(private booksService: BooksService, private ordersService: OrdersService) { }

  ngOnInit(): void {
    this.counter = 0;
    /*let test: Order = new Order();
    test.orderId = 1;
    test.ISBN = 1;
    test.quantity = 10;
    this.orders.push(test);*/
    this.ordersService.getAllOrders().subscribe({
      next: (orders) => {
        this.orders = orders;
        this.ordersToView = orders;
        this.fillOrderToView();
      },
      error: (err) => alert(err),
    });
  }
  
  fillOrderToView() {
    for(let i = 0; i<this.orders.length; i++){
      this.getBookTitle(this.orders[i].isbn);
    }
  }

  openConfirmOrderModal(orderId:number){
    this.OrderId = orderId;
    document.getElementById('openConfirmOrderBtn')?.click();
  }

  getBookTitle(bookISBN:number){
    let book : Book = new Book();
    this.booksService.getBookByISBN(bookISBN).subscribe(
      (response) => {
        book = response;
        this.ordersToView[this.counter].title = book.title;
        this.counter++;
      },
      (err) => {
        alert(err);
      }
    );
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
