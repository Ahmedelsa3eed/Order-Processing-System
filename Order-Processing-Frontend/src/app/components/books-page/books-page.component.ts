import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Book } from 'src/app/models/Book';
import { Order } from 'src/app/models/Order';
import { BooksService } from 'src/app/services/books.service';
import { OrdersService } from 'src/app/services/orders.service';
import { Publisher } from 'src/app/models/Publisher';
import { PublisherService } from 'src/app/services/publisher.service';
import { SignInOutService } from 'src/app/services/sign-in-out.service';

@Component({
  selector: 'app-books-page',
  templateUrl: './books-page.component.html',
  styleUrls: ['./books-page.component.css']
})
export class BooksPageComponent implements OnInit {

  constructor(private signInOutService: SignInOutService, private booksService: BooksService, private ordersService: OrdersService, private publisherService: PublisherService) { }

  books: Book[] = [];
  publishers: Publisher[] = [];
  bookToDeleteISBN: number = -1;
  bookToOrderISBN: number = -1;
  bookToEdit: Book = new Book();
  deleteBookLoading: boolean = false;
  orderBookLoading: boolean = false;
  editBookLoading: boolean = false;
  addBookLoading: boolean = false;
  signedInUserType: string = this.signInOutService.getSignedInUserType();

  ngOnInit(): void {
    this.publisherService.getAll().subscribe({
      next: (publishers) => {
        this.publishers = publishers;
      },
      error: (err) => alert(err), 
    });
    this.booksService.getAllBooks().subscribe({
      next: (books) => {
        this.books = books;
      },
      error: (err) => alert(err),
    });
  }

  onAddBook(title: string, ISBN: string, publisher_id: string, pubYear: string, price: string, category: string, quantity: string, threshold: string) {
    this.addBookLoading = true;
    let book: Book = new Book();
    book.title = title;
    book.isbn = Number(ISBN);
    book.publisher_id = Number(publisher_id);
    book.publication_year = Number(pubYear);
    book.price = Number(price);
    book.category = category;
    book.quantity = Number(quantity);
    book.threshold = Number(threshold);
    this.booksService.addBook(book).subscribe(
      () => {
        this.addBookLoading = false;
        document.getElementById('closeAddBookBtn')?.click();
        window.location.reload();
      },
      (error: HttpErrorResponse) => {
        this.addBookLoading = false;
        alert('Something is wrong, adding the book!');
      }
    );
  }

  openDeleteBookModal(bookId: number) {
    this.bookToDeleteISBN = bookId;
    document.getElementById('openDeleteBookBtn')?.click();
  }

  openEditBookModal(book: Book) {
    this.bookToEdit = book;
    console.log(book);
    document.getElementById('openEditBookBtn')?.click();
    document.getElementById('editBookTitleInput')?.setAttribute('value', book.title);
    document.getElementById('editBookISBNInput')?.setAttribute('value', String(book.isbn));
    document.getElementById('editBookPubYearInput')?.setAttribute('value', String(book.publication_year));
    document.getElementById('editBookPriceInput')?.setAttribute('value', String(book.price));
    document.getElementById('editBookQuantityInput')?.setAttribute('value', String(book.quantity));
    document.getElementById('editBookThresholdInput')?.setAttribute('value', String(book.threshold));
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

  onEditBook(title: string, publisher_id: string, pubYear: string, price: string, category: string, quantity: string, threshold: string) {
    this.editBookLoading = true;
    this.bookToEdit.title = title;
    this.bookToEdit.publisher_id = Number(publisher_id);
    this.bookToEdit.publication_year = Number(pubYear);
    this.bookToEdit.price = Number(price);
    this.bookToEdit.category = category;
    this.bookToEdit.quantity = Number(quantity);
    this.bookToEdit.threshold = Number(threshold);
    this.booksService.editbook(this.bookToEdit).subscribe(
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
