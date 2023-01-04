import { Injectable } from '@angular/core';
import { User } from '../models/User';
import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { CookieService } from 'ngx-cookie-service';

@Injectable({
    providedIn: 'root',
})
export class SignInOutService {
    constructor(private httpClient: HttpClient, private cookieService: CookieService) {}

    public signIn(email: string, password: string): Observable<HttpResponse<string>> {
        let httpParams = new HttpParams();
        httpParams = httpParams.append('email', email);
        httpParams = httpParams.append('password', password);
        return this.httpClient.get(environment.baseUrl + '/logIn/logIn', {
            observe: 'response',
            params: httpParams,
            responseType: 'text',
        });
    }

    public signOut(): Observable<any> {
        this.cookieService.deleteAll('/', '', true, 'Strict');
        return this.httpClient
        .put(
            environment.baseUrl + '/logIn/logout',
            {},
            { params: { sessionID: this.cookieService.get('session_id') } }
        );
    }

    public forgotPassword(email: string): Observable<HttpResponse<any>> {
        let httpParams = new HttpParams();
        httpParams = httpParams.append('email', email);
        return this.httpClient.get(environment.baseUrl + '/logIn/forgetPassword', {
            params: httpParams,
            observe: 'response',
            responseType: 'text',
        });
    }

    // This function should be called in appropriate components' onInit
    public isSignedIn(): boolean {
        return this.cookieService.check('session_id');
    }

    // This function should be called when the user sign In for the first time, or when he edits his information.
    public fillSignedInUserInfo(sessionId: string): Observable<HttpResponse<User>> {
        return this.httpClient.get<User>(environment.baseUrl + '/logIn/getUser', {
            params: { sessionID: sessionId },
            observe: 'response',
            responseType: 'json',
        });
    }
    
    public getSignedInUser(): User {
        let user: User = new User();
        user.user_id = this.getSignedInUserId();
        user.user_name = this.getSignedInUserName();
        user.first_name = this.getSignedInUserFirstName();
        user.last_name = this.getSignedInUserLastName();
        user.address = this.getSignedInUserAddress();
        user.phone_number = this.getSignedInUserPhonenumber();
        user.email = this.getSignedInUserEmail();
        user.type = this.getSignedInUserType();
        user.session_id = this.getSignedInUserSessionID();
        return user;
    }

    public getSignedInUserId(): number {
        return Number(this.cookieService.get('user_id'));
    }

    public getSignedInUserName(): string {
        return this.cookieService.get('user_name');
    }

    public getSignedInUserFirstName(): string {
        return this.cookieService.get('first_name');
    }

    public getSignedInUserLastName(): string {
        return this.cookieService.get('last_name');
    }

    public getSignedInUserAddress(): string {
        return this.cookieService.get('address');
    }

    public getSignedInUserPhonenumber(): string {
        return this.cookieService.get('phone_number');
    }

    public getSignedInUserEmail(): string {
        return this.cookieService.get('email');
    }

    public getSignedInUserType(): string {
        return this.cookieService.get('type');
    }

    public getSignedInUserSessionID(): string {
        return this.cookieService.get('session_id');
    }
}
