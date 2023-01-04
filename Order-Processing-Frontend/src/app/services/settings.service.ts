import {ChangeNameRequest} from "../models/settings/ChangeNameRequest";
import { environment } from 'src/environments/environment';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {ChangePasswordRequest} from "../models/settings/ChangePasswordRequest";
import {SignInOutService} from "./sign-in-out.service";
import {Injectable} from "@angular/core";
@Injectable({
  providedIn: 'root'
})
export class SettingsService {
  constructor(private http: HttpClient, private signInOutService: SignInOutService) {}

  changeName(request: ChangeNameRequest): Observable<string> {
    return this.http.put<string>(`${environment.baseUrl}/settings/changeName`, request, {
      params: { sessionId: this.signInOutService.getSignedInUserSessionID() },
    });
  }

  changeUsername(username: string): Observable<string> {
    return this.http.put<string>(`${environment.baseUrl}/settings/changeUsername`, username, {
      params: { sessionId: this.signInOutService.getSignedInUserSessionID() },
    });
  }

  changePassword(request: ChangePasswordRequest): Observable<string> {
    return this.http.put<string>(`${environment.baseUrl}/settings/changePassword`, request, {
      params: { sessionId: this.signInOutService.getSignedInUserSessionID() },
    });
  }

  changeEmail(email: string): Observable<string> {
    return this.http.put<string>(`${environment.baseUrl}/settings/changeEmail`, email, {
      params: { sessionId: this.signInOutService.getSignedInUserSessionID() },
    });

  }

  changeAddress(address: string): Observable<string> {
    return this.http.put<string>(`${environment.baseUrl}/settings/changeAddress`, address, {
      params: { sessionId: this.signInOutService.getSignedInUserSessionID() },
    });
  }

  changePhoneNumber(phoneNumber: string): Observable<string> {
    return this.http.put<string>(`${environment.baseUrl}/settings/changePhone`, phoneNumber, {
      params: { sessionId: this.signInOutService.getSignedInUserSessionID() },
    });
  }

  changeUserName(userName: string): Observable<string> {
    return this.http.put<string>(`${environment.baseUrl}/settings/changeUserName`, userName, {
      params: { sessionId: this.signInOutService.getSignedInUserSessionID() },
    });
  }



}
