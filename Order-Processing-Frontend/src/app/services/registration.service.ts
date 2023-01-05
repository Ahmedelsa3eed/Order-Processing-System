import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import {RegistrationRequest} from "../models/RegistrationRequest";

@Injectable({
  providedIn: 'root',
})
export class RegistrationService {
  constructor(private http: HttpClient) {}

  // post sign up data to the backend
  postSignUpData(registrationRequest: RegistrationRequest): Observable<HttpResponse<string>> {
    return this.http.post(`${environment.baseUrl}/registration/register`, registrationRequest, {
      observe: 'response',
      responseType: 'text',
    });
  }
}
