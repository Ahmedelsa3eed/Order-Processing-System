import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SignInOutService } from './sign-in-out.service';
import { Publisher } from '../models/Publisher';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class PublisherService {

  constructor(private httpClient: HttpClient, private signInOutService: SignInOutService) { }


  addPublisher(publisher: Publisher): Observable<any> {
    return this.httpClient.post<any>(environment.baseUrl+'/Publisher/manager/addPublisher', publisher, {
      params: {sessionId: this.signInOutService.getSignedInUserSessionID()},
    });
  }

  getAll(): Observable<any>{
    return this.httpClient.get<any>(environment.baseUrl + '/Publisher/getAllPublisher');
  }

  editPublisher(publisher:Publisher): Observable<any>{
    return this.httpClient.put<any>(environment.baseUrl+'/Publisher/manager/editPublisher', publisher, {
      params: {sessionId: this.signInOutService.getSignedInUserSessionID()},
    });
  }
  deletePublisher(name:string): Observable<any>{
    return this.httpClient.delete<any>(environment.baseUrl+'/Publisher/manager/deletePublisher', {
      params: {
        sessionId: this.signInOutService.getSignedInUserSessionID(),
        name: name,
      },
    });
  }

  getPublisherFromTo(from:number, to:number){
    return this.httpClient.get<any>(environment.baseUrl + '/Publisher/getFromTo', {
      params: {
        from: from,
        to: to,
      },
    });
  }
}
