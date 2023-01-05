import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {SignInOutService} from "./sign-in-out.service";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {Author} from "../models/Author";

@Injectable({
  providedIn: 'root'
})
export class AuthorService {
  constructor(private httpClient: HttpClient, private signInOutService: SignInOutService) { }


  addAuthor(author: Author): Observable<any> {
    return this.httpClient.post<any>(environment.baseUrl+'/Author/manager/addAuthor', author, {
      params: {sessionId: this.signInOutService.getSignedInUserSessionID()},
    });
  }

  getAll(): Observable<any>{
    return this.httpClient.get<any>(environment.baseUrl + '/Author/getAllAuthor');
  }

  getAuthorsFromTo(from:number, to:number) {
    return this.httpClient.get<any>(environment.baseUrl + '/Author/getFromTo', {
      params: {
        from: from,
        to: to,
      },
    });
  }

  editAuthor(author:Author): Observable<any>{
    return this.httpClient.put<any>(environment.baseUrl+'/Author/manager/editAuthor', author, {
      params: {sessionId: this.signInOutService.getSignedInUserSessionID()},
    });
  }

  deleteAuthor(authorId:number): Observable<any>{
    return this.httpClient.delete<any>(environment.baseUrl+'/Author/manager/deleteAuthor', {
      params: {
        sessionId: this.signInOutService.getSignedInUserSessionID(),
        author_id: authorId
      },
    });
  }
}
