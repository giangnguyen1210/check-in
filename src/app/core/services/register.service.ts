import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment.development';
import { HeaderService } from './header.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient, private headerService: HeaderService) { }
  
  getRegisterList(json: any): Observable<any> {
    const url = `${this.apiUrl}/admin/register/list`;
    const headers = this.headerService.getHeaders()
    return this.http.post(url, json,{ headers });
  }

  createRegister(json: any): Observable<any> {
    const url = `${this.apiUrl}/admin/register/create`
    const headers = this.headerService.getHeaders()
    return this.http.post(url, json, { headers });
  }
  updateRegister(user:any): Observable<any>{
    const headers = this.headerService.getHeaders();
    const url = `${this.apiUrl}/admin/register/update`;
    return this.http.post(url, user, { headers });
    
  }

  deleteRegister(user:any): Observable<any>{
    const headers = this.headerService.getHeaders();
    const url = `${this.apiUrl}/admin/register/delete`;
    return this.http.post(url, user, { headers });
    
  }

}
