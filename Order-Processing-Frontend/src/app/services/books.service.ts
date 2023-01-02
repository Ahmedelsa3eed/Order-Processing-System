import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Book } from '../models/Book';
import { SignInOutService } from './sign-in-out.service';

@Injectable({
  providedIn: 'root'
})
export class BooksService {
  constructor(private httpClient: HttpClient, private signInOutService: SignInOutService) { }

  editbook(book: Book) {
    return this.httpClient.put(environment.baseUrl + '/books/admin/editBook', book, {
      params: { sessionId: this.signInOutService.getSignedInUserSessionID() },
    });
  }

  deleteBook(bookISBN: number) {
    return this.httpClient.delete(environment.baseUrl + '/books/admin/deleteBook', {
      params: { sessionId: this.signInOutService.getSignedInUserSessionID(), ISBN: bookISBN },
    });
  }
}
