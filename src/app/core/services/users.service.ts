import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment.development';
import { HeaderService } from './header.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient, private headerService: HeaderService) { }
  
  getUserList(json: any): Observable<any> {
    const url = `${this.apiUrl}/admin/users/list`;
    const headers = this.headerService.getHeaders()
    return this.http.post(url, json,{ headers });
  }

  createUser(user: any): Observable<any> {
    const url = `${this.apiUrl}/admin/users/create`
    const headers = this.headerService.getHeaders()
    return this.http.post(url, user, { headers });
  }
  updateUser(user:any): Observable<any>{
    const headers = this.headerService.getHeaders();
    const url = `${this.apiUrl}/admin/users/update`;
    return this.http.post(url, user, { headers });
    
  }

  deActivateUser(user: any): Observable<any> {
    const headers = this.headerService.getHeaders();
    const url = `${this.apiUrl}/admin/users/deactivate`;
    return this.http.post(url, user, { headers });
  }

  activateUser(user: any): Observable<any> {
    const headers = this.headerService.getHeaders();
    const url = `${this.apiUrl}/admin/users/activate`;
    return this.http.post(url, user, { headers });
  }

  statusUser(user: any): Observable<any> {
    const headers = this.headerService.getHeaders();
    const url = `${this.apiUrl}/admin/users/status`;
    return this.http.post(url, user, { headers });
  }

}
