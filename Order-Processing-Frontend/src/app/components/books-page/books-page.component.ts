import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Book } from 'src/app/models/Book';
import { OrderToPlace } from 'src/app/DTOs/OrderToPlace';
import { BooksService } from 'src/app/services/books.service';
import { OrdersService } from 'src/app/services/orders.service';
import { PublisherService } from 'src/app/services/publisher.service';
import { SignInOutService } from 'src/app/services/sign-in-out.service';
import { CartService } from 'src/app/services/cart.service';

@Component({
  selector: 'app-books-page',
  templateUrl: './books-page.component.html',
  styleUrls: ['./books-page.component.css']
})
export class BooksPageComponent implements OnInit {

  books: Book[] = [];
  bookToDeleteISBN: number = -1;
  bookToOrderISBN: number = -1;
  bookToEdit: Book = new Book();
  deleteBookLoading: boolean = false;
  orderBookLoading: boolean = false;
  editBookLoading: boolean = false;
  addBookLoading: boolean = false;
  findBookCriteriaInputValue: string = "Select Criteria";
  findBookFirstNameInput: string = "";
  findBookLastNameInput: string = "";
  searchInput: string = "";
  pageNum: number = 0;
  findPageNum: number = 0;
  booksPerPage: number = 17;
  signedInUserType: string = this.signInOutService.getSignedInUserType();

  constructor(
    private signInOutService: SignInOutService,
    private booksService: BooksService,
    private ordersService: OrdersService,
    private publisherService: PublisherService,
    private cartService: CartService) { }

  ngOnInit(): void {
    this.booksService.getBooksFromTo(0, this.booksPerPage).subscribe({
      next: (books) => {
        this.books = books;
        this.getBooksPublisher();
        this.getBooksAuthors();
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
    document.getElementById('openEditBookBtn')?.click();
    document.getElementById('editBookTitleInput')?.setAttribute('value', book.title);
    document.getElementById('editBookISBNInput')?.setAttribute('value', String(book.isbn));
    document.getElementById('editBookPublisherInput')?.setAttribute('value', String(book.publisher.publisher_id));
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

    let orderToPlace : OrderToPlace = new OrderToPlace();
    orderToPlace.ISBN = this.bookToOrderISBN;
    console.log("ISBN of book sent is " + orderToPlace.ISBN);
    orderToPlace.quantity = parseInt(quanitty);

    this.ordersService.orderBook(orderToPlace).subscribe(()=>{
      this.orderBookLoading = false;
      document.getElementById('closeOrderBookBtn')?.click();
      window.location.reload();
    });
    console.log("ISBN of book sent is " + orderToPlace.ISBN);
  }

  onEditBook(title: string, isbn: string, publisher_id: string, pubYear: string, price: string, category: string, quantity: string, threshold: string) {
    this.editBookLoading = true;
    this.bookToEdit.title = title;
    this.bookToEdit.isbn = Number(isbn);
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

  onAddBookAuthor(isbn: string, author_id: string) {
    this.booksService.addBookAuthor(Number(isbn), Number(author_id)).subscribe(
      () => {
          document.getElementById('closeAddBookAuthorBtn')?.click();
          window.location.reload();
      },
      (error: HttpErrorResponse) => {
          alert(error);
      }
    );
  }

  onDeleteBookAuthor(isbn: string, author_id: string) {
    this.booksService.deleteBookAuthor(Number(isbn), Number(author_id)).subscribe(
      () => {
          document.getElementById('closeDeleteBookAuthorBtn')?.click();
          window.location.reload();
      },
      (error: HttpErrorResponse) => {
          alert(error);
      }
    );
  }

  getBooksPublisher() {
    for (let i = 0; i < this.books.length; i++)
    {
      this.getPublisherByISBN(i);
    }
  }

  getPublisherByISBN(index: number) {
    this.booksService.getPublisherByISBN(this.books[index].isbn).subscribe({
      next: (publisher) => {
        this.books[index].publisher = publisher;
      },
      error: (err) => alert(err),
    })
  }

  getBooksAuthors() {
    for (let i = 0; i < this.books.length; i++)
    {
      this.getAuthorsByISBN(i);
    }
  }

  getAuthorsByISBN(index: number) {
    this.booksService.getAuthorsByISBN(this.books[index].isbn).subscribe({
      next: (authors) => {
        this.books[index].authors = authors;
      },
      error: (err) => {},
    })
  }

  onFindBook(findPageNum: number) {
    this.findPageNum = findPageNum;
    if(this.findBookCriteriaInputValue == "Select Criteria") {
    }else if(this.findBookCriteriaInputValue == "isbn") {
      this.booksService.getBookByISBN(Number(this.searchInput)).subscribe(
        (book) => {
          this.books = [];
          this.books.push(book);
          document.getElementById('closeFindBookBtn')?.click();
          this.getBooksPublisher();
          this.getBooksAuthors();
        },
        (err) => { alert(err); }
      )
    }else if(this.findBookCriteriaInputValue == "publishers") {
      this.booksService.findBooksByPublisherName(this.searchInput, this.findPageNum * this.booksPerPage, (this.findPageNum + 1) * this.booksPerPage).subscribe({
        next: (books) => {
          this.books = books;
          document.getElementById('closeFindBookBtn')?.click();
          this.getBooksPublisher();
          this.getBooksAuthors();
        },
        error: (err) => alert(err),
      });
    }else if(this.findBookCriteriaInputValue == "authors") {
      this.booksService.findBooksByAuthorName(this.findBookFirstNameInput, this.findBookLastNameInput, this.findPageNum * this.booksPerPage, (this.findPageNum + 1) * this.booksPerPage).subscribe({
        next: (books) => {
          this.books = books;
          document.getElementById('closeFindBookBtn')?.click();
          this.getBooksPublisher();
          this.getBooksAuthors();
        },
        error: (err) => alert(err),
      });
    }else {
      this.booksService.findBooksByAttribute(this.findBookCriteriaInputValue, this.searchInput, this.findPageNum * this.booksPerPage, (this.findPageNum + 1) * this.booksPerPage).subscribe({
        next: (books) => {
          this.books = books;
          document.getElementById('closeFindBookBtn')?.click();
          this.getBooksPublisher()
          this.getBooksAuthors();
        },
        error: (err) => alert(err),
      });
    }
  }

  getNextPage() {
    if (this.books.length < this.booksPerPage)
      return;
    this.pageNum++;
    if (this.findBookCriteriaInputValue == "Select Criteria") {
      this.booksService.getBooksFromTo(this.pageNum * this.booksPerPage, (this.pageNum + 1) * this.booksPerPage).subscribe({
        next: (books) => {
          this.books = books;
          this.getBooksPublisher();
          this.getBooksAuthors();
        },
        error: (err) => alert(err),
      });
    }else {
      this.findPageNum++;
      this.onFindBook(this.findPageNum);
    }
  }

  getPreviousPage() {
    if (this.pageNum == 0)
      return;
    this.pageNum--;
    if (this.findBookCriteriaInputValue == "Select Criteria") {
      this.booksService.getBooksFromTo(this.pageNum * this.booksPerPage, (this.pageNum + 1) * this.booksPerPage).subscribe({
        next: (books) => {
          this.books = books;
          this.getBooksPublisher();
          this.getBooksAuthors();
        },
        error: (err) => alert(err),
      });
    }else {
      this.findPageNum--;
      this.onFindBook(this.findPageNum);
    }
  }

  changeCriteria(findBookCriteriaInputValue: string) {
    this.findBookCriteriaInputValue = findBookCriteriaInputValue;
  }

  setFirstName(findBookFirstNameInput: string) {
    this.findBookFirstNameInput = findBookFirstNameInput;
  }

  setLastName(findBookLastNameInput: string) {
    this.findBookLastNameInput = findBookLastNameInput;
  }

  setSearchInput(searchInput: string) {
    this.searchInput = searchInput;
  }

  addToCart(isbn: number) {
    this.cartService.addToCart(isbn).subscribe({
      next: (res) => {
        if (res == true) {
          window.alert("Book added to cart");
        }
      }
    })
  }

}
