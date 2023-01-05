import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { from, Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Book } from '../models/Book';
import { SignInOutService } from './sign-in-out.service';

@Injectable({
  providedIn: 'root'
})
export class BooksService {
  constructor(private httpClient: HttpClient, private signInOutService: SignInOutService) { }

  getAllBooks(): Observable<any> {
    return this.httpClient.get<any>(environment.baseUrl + '/books/getAllBooks');
  }

  getBooksFromTo(from: number, to: number): Observable<any> {
    return this.httpClient.get<any>(environment.baseUrl + '/books/getBooksFromTo', {
      params: {
        from: from,
        to: to
      },
    });
  }

  addBook(book: Book): Observable<any> {
    return this.httpClient.post<any>(environment.baseUrl+'/books/manager/addBook', book, {
      params: {sessionId: this.signInOutService.getSignedInUserSessionID()},
    });
  }

  editbook(book: Book): Observable<any> {
    return this.httpClient.put(environment.baseUrl + '/books/manager/editBook', book, {
      params: { sessionId: this.signInOutService.getSignedInUserSessionID() },
    });
  }

  deleteBook(bookISBN: number): Observable<any> {
    return this.httpClient.delete(environment.baseUrl + '/books/manager/deleteBook', {
      params: { sessionId: this.signInOutService.getSignedInUserSessionID(), ISBN: bookISBN },
    });
  }

  getBookByISBN(bookISBN: number): Observable<any> {
    return this.httpClient.get<any>(environment.baseUrl + '/books/getBookByISBN', {
      params: { ISBN: bookISBN },
    });
  }

  findBooksByAttribute(criteria: string, searchInput: string, from: number, to: number): Observable<any> {
    return this.httpClient.get<any>(environment.baseUrl + '/books/findBooksByAttribute', {
      params: { criteria: criteria, searchInput: searchInput, from: from, to: to },
    });
  }

  findBooksByPublisherName(name: string, from: number, to: number): Observable<any> {
    return this.httpClient.get<any>(environment.baseUrl + '/books/findBooksByPublisherName', {
      params: { name: name, from: from, to: to },
    });
  }

  findBooksByAuthorName(first_name: string, last_name: string, from: number, to: number): Observable<any> {
    return this.httpClient.get<any>(environment.baseUrl + '/books/findBooksByAuthorName', {
      params: { first_name: first_name, last_name: last_name, from: from, to: to },
    });
  }

  getPublisherByISBN(ISBN: number): Observable<any> {
    return this.httpClient.get<any>(environment.baseUrl + '/Publisher/getPublisherByISBN', {
      params: { ISBN: ISBN },
    });
  }

  getAuthorsByISBN(ISBN: number): Observable<any> {
    return this.httpClient.get<any>(environment.baseUrl + '/Author/getAuthorsByISBN', {
      params: { ISBN: ISBN },
    });
  }

  addBookAuthor(ISBN: number, author_id: number): Observable<any> {
    return this.httpClient.put(environment.baseUrl + '/books/manager/addBookAuthor', {}, {
      params: { sessionId: this.signInOutService.getSignedInUserSessionID(), ISBN: ISBN, author_id: author_id },
    });
  }

  deleteBookAuthor(ISBN: number, author_id: number): Observable<any> {
    return this.httpClient.delete(environment.baseUrl + '/books/manager/deleteBookAuthor', {
      params: { sessionId: this.signInOutService.getSignedInUserSessionID(), ISBN: ISBN, author_id: author_id },
    });
  }

}
