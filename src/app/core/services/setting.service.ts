import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment.development';
import { HeaderService } from './header.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SettingService {

  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient, private headerService: HeaderService) { }
  
  getSettingPassword(json: any): Observable<any> {
    const url = `${this.apiUrl}/admin/setting/get-password`;
    const headers = this.headerService.getHeaders()
    return this.http.post(url, json,{ headers });
  }

  createSettingPassword(json: any): Observable<any> {
    const url = `${this.apiUrl}/admin/setting/create-password`
    const headers = this.headerService.getHeaders()
    return this.http.post(url, json, { headers });
  }
  updateSettingPassword(json:any): Observable<any>{
    const headers = this.headerService.getHeaders();
    const url = `${this.apiUrl}/admin/setting/update-password`;
    return this.http.post(url, json, { headers });
  }

  getSettingSession(json: any): Observable<any> {
    const url = `${this.apiUrl}/admin/setting/get-session`;
    const headers = this.headerService.getHeaders()
    return this.http.post(url, json,{ headers });
  }

  createSettingSession(json: any): Observable<any> {
    const url = `${this.apiUrl}/admin/setting/create-session`
    const headers = this.headerService.getHeaders()
    return this.http.post(url, json, { headers });
  }
  updateSettingSession(json:any): Observable<any>{
    const headers = this.headerService.getHeaders();
    const url = `${this.apiUrl}/admin/setting/update-session`;
    return this.http.post(url, json, { headers });
  }

}
