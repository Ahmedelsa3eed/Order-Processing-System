import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Book } from 'src/app/models/Book';
import { Order } from 'src/app/models/Order';
import { BooksService } from 'src/app/services/books.service';
import { OrdersService } from 'src/app/services/orders.service';
import { SignInOutService } from 'src/app/services/sign-in-out.service';

@Component({
  selector: 'app-books-page',
  templateUrl: './books-page.component.html',
  styleUrls: ['./books-page.component.css']
})
export class BooksPageComponent implements OnInit {

  constructor(private signInOutService: SignInOutService, private booksService: BooksService, private ordersService: OrdersService) { }

  loggedInUserRole = this.signInOutService.getSignedInUserType();
  books: Book[] = [];
  bookToDeleteISBN: number = -1;
  bookToEditISBN: number = -1;
  bookToOrderISBN: number = -1;
  deleteBookLoading: boolean = false;
  orderBookLoading: boolean = false;
  editBookLoading: boolean = false;
  addBookLoading: boolean = false;
  signedInUserType: string = this.signInOutService.getSignedInUserType();

  ngOnInit(): void {
    let test: Book = new Book();
    test.title = "test book";
    this.books.push(test);
  }

  onAddBook(bookTitle: string) {
    this.addBookLoading = true;
    let book: Book = new Book();
    book.title = bookTitle;
    // send request
  }

  openDeleteBookModal(bookId: number) {
    this.bookToDeleteISBN = bookId;
    document.getElementById('openDeleteBookBtn')?.click();
  }

  openEditBookModal(bookId: number, bookTitle: string) {
    this.bookToEditISBN = bookId;
    document.getElementById('openEditBookBtn')?.click();
    document.getElementById('editBookTitleInput')?.setAttribute('value', bookTitle);
  }

  openOrderBookModal(bookId: number, bookTitle: string) {
    this.bookToOrderISBN = bookId;
    document.getElementById('openOrderBookBtn')?.click();
    document.getElementById('orderBookTitleInput')?.setAttribute('value', bookTitle);
  }

  onDeleteBook() {
    this.deleteBookLoading = true;
    this.booksService.deleteBook(this.bookToDeleteISBN).subscribe(() => {
        this.deleteBookLoading = false;
        document.getElementById('closeDeleteBookBtn')?.click();
        window.location.reload();
    });
  }

  onOrderBook(quanitty: string){
    this.orderBookLoading = true;

    let order : Order = new Order();
    order.ISBN = this.bookToOrderISBN;
    order.quantity = parseInt(quanitty);

    this.ordersService.orderBook(order).subscribe(()=>{
      this.orderBookLoading = false;
      document.getElementById('closeOrderBookBtn')?.click();
      window.location.reload();
    });
  }

  onEditBook(bookTitle: string) {
    this.editBookLoading = true;
    let book: Book = new Book();
    book.ISBN = this.bookToEditISBN;
    book.title = bookTitle;
    this.booksService.editbook(book).subscribe(
        () => {
            this.editBookLoading = false;
            document.getElementById('closeEditBookBtn')?.click();
            window.location.reload();
        },
        (error: HttpErrorResponse) => {
            this.editBookLoading = false;
            alert('Something is wrong, editing the book!');
        }
    );
  }

}
