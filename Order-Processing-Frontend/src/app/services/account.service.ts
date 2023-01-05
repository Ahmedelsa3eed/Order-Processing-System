import { SignInOutService } from './sign-in-out.service';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../models/User';

@Injectable({
  providedIn: 'root',
})
export class AccountService {
  private url = environment.baseUrl + '/accounts';

  constructor(
    private http: HttpClient,
    private userService: SignInOutService
  ) {}

  public getAccounts(): Observable<HttpResponse<User[]>> {
    let sessionId = this.userService.getSignedInUserSessionID();
    return this.http.get<User[]>(this.url + '/all', {
      observe: 'response',
      params: {
        sessionId: sessionId,
      },
      responseType: 'json',
    });
  }

  public getAccountFromTo(from:number, to:number){
    let sessionId = this.userService.getSignedInUserSessionID();
    return this.http.get<User[]>(this.url + '/fromTo', {
      observe: 'response',
      params: {
        sessionId: sessionId,
        from: from,
        to: to,
      },
      responseType: 'json',
    });
  }

  public searchAccounts(
    searchString: string
  ): Observable<HttpResponse<User[]>> {
    let sessionId = this.userService.getSignedInUserSessionID();
    return this.http.get<User[]>(this.url + '/search', {
      observe: 'response',
      responseType: 'json',
      params: {
        sessionId: sessionId,
        searchString: searchString,
      },
    });
  }

  public changeRole(
    userId: number,
    newType: string
  ): Observable<HttpResponse<any>> {
    let sessionId = this.userService.getSignedInUserSessionID();
    return this.http.put<boolean>(
      this.url + '/changeUserType',
      {},
      {
        observe: 'response',
        responseType: 'json',
        params: {
          sessionId: sessionId,
          userId: userId,
          type: newType,
        },
      }
    );
  }

  public deleteAccount(userId: number): Observable<HttpResponse<any>> {
    let sessionId = this.userService.getSignedInUserSessionID();
    return this.http.delete(this.url + '/delete', {
      observe: 'response',
      params: {
        sessionId: sessionId,
        userId: userId,
      },
      responseType: 'json',
    });
  }
}
