import { Component, OnInit } from '@angular/core';
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
  constructor(private booksService: BooksService, private ordersService: OrdersService) { }

  ngOnInit(): void {
    /*let test: Order = new Order();
    test.orderId = 1;
    test.ISBN = 1;
    test.quantity = 10;
    this.orders.push(test);*/
    this.ordersService.getAllOrders().subscribe({
      next: (orders) => {
        this.orders = orders;
      },
      error: (err) => alert(err),
    });
  }

  openConfirmOrderModal(orderId:number){
    this.OrderId = orderId;
    document.getElementById('openConfirmOrderBtn')?.click();
  }

  getBookTitle(bookISBN:number){
    let book : Book = new Book();
    book = this.booksService.getBookById(bookISBN);
    return book.title;
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
